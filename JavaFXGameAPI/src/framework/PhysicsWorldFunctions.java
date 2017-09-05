package framework;

import bodies.BodyPropertiesOwner;
import bodies.Physical;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.jbox2d.collision.Collision;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import utilites.CoordinateConverter;
import utilites.PositionHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PhysicsWorldFunctions {
    private static Map<Node, Body> nodeBodyMap;
    private static Map<Node, Fixture> nodeFixtureMap;
    private static CoordinateConverter coordinateConverter;

    private static Collection<KeyCode> keysPressed = new ArrayList<>();
    private static Collision collision;

    private static PositionHelper positionHelper = new PositionHelper();

    static void setup(World world, Map<Node, Body> nodeBodyMap, Map<Node, Fixture> nodeFixtureMap, CoordinateConverter coordinateConverter){
        PhysicsWorldFunctions.nodeBodyMap = nodeBodyMap;
        PhysicsWorldFunctions.nodeFixtureMap = nodeFixtureMap;
        PhysicsWorldFunctions.coordinateConverter = coordinateConverter;
        PhysicsWorldFunctions.keysPressed = new ArrayList<>(); //reset keys pressed
        PhysicsWorldFunctions.collision = new Collision(world.getPool());
    }

    public static Stage getStage(Node node){
        return (Stage)node.getScene().getWindow();
    }

    public static Point2D getVectorForDegrees(double degrees, double force){
        double radian = Math.toRadians(degrees);
        return new Point2D((float)(Math.sin(radian) * force), (float)-(Math.cos(radian) * force));
    }

    public static Point2D getRotatedLayoutPosition(Node node, double offsetX, int offsetY) {

        return getRotatedLayoutPosition(node, offsetX, offsetY, node.getRotate());
    }

    public static Point2D getRotatedLayoutPosition(Node node, double offsetX, int offsetY, double rotate) {

        Bounds bounds = node.getBoundsInParent();
        double x = bounds.getMinX() + offsetX;
        double y = bounds.getMinY() + offsetY;
        double radian = Math.toRadians(rotate);
        Point2D center = positionHelper.getCenter(bounds);
        double cX = center.getX();
        double cY = center.getY();

        x -= cX;
        y -= cY;

        double cos = Math.cos(radian);
        double sin = Math.sin(radian);

        double rotatedX  = cos * x - sin * y;
        double rotatedY = sin * x + cos * y;

        x = rotatedX;
        y = rotatedY;

        x += cX;
        y += cY;

        return new Point2D(x, y);
    }

    public static boolean physicsNodesTouching(Node node1, Node node2){

        Body body1 = nodeBodyMap.containsKey(node1) ? nodeBodyMap.get(node1) : nodeBodyMap.get(node1.getParent());
        Body body2 = nodeBodyMap.containsKey(node2) ? nodeBodyMap.get(node2) : nodeBodyMap.get(node2.getParent());

        Shape shape1 = nodeFixtureMap.containsKey(node1)  ? nodeFixtureMap.get(node1).getShape() : body1.getFixtureList().getShape();
        Shape shape2 = nodeFixtureMap.containsKey(node2)  ? nodeFixtureMap.get(node2).getShape() : body2.getFixtureList().getShape();

        return collision.testOverlap(shape1, 0, shape2, 0, body1.getTransform(), body2.getTransform());
    }

    public static boolean hasStyle(Node node, String style){
        return node.getStyleClass().contains(style);
    }

    public static void registerKeyPressed(KeyCode keyCode){
        if (!keysPressed.contains(keyCode)) {
            keysPressed.add(keyCode);
        }
    }

    public static void registerKeyReleased(KeyCode keyCode){
        keysPressed.remove(keyCode);
    }

    public static boolean keyIsPressed(KeyCode keyCode){
        return keysPressed.contains(keyCode);
    }

    public Point2D getCenterOfMass(Node node) throws Exception {
        if (!nodeBodyMap.containsKey(node))
            throw new Exception("Node must be the outermost physical node", new Throwable());

        Body body = nodeBodyMap.get(node);
        return coordinateConverter.convertWorldPointToScreen(body.getWorldCenter(), node.getParent());
    }

    public static <T extends Node & Physical> double getCurrentSpeed(T node){
        return Math.abs(node.getLinearVelocityX())  + Math.abs(node.getLinearVelocityY());
    }

    public static <T extends Node & Physical> void setSpeedToCurrentTravelVector(T node, double speed){
        double currentSpeed = getCurrentSpeed(node);
        double directionX =  node.getLinearVelocityX() / currentSpeed;
        double directionY =  node.getLinearVelocityY() / currentSpeed;
        node.setLinearVelocityX(directionX * speed);
        node.setLinearVelocityY(directionY * speed);
    }

    public static <T extends Node & Physical> Point2D getOffsetTravelVector(T node, double angleOffset) {
        double currentSpeed = getCurrentSpeed(node);
        double directionX =  node.getLinearVelocityX() / currentSpeed;
        double directionY =  node.getLinearVelocityY() / currentSpeed;

        //Invert directionY because javaFX direction is reversed from normal math direction
        double degrees = Math.toDegrees(Math.atan2(directionX, -directionY)) + angleOffset;
        return getVectorForDegrees(degrees, currentSpeed);
    }

    public static <T> T loadResource(Class<?> relativeClass, String resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(relativeClass.getResource(resource));
        return loader.load();
    }
}