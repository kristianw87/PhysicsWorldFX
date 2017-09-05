package framework.nodes;

import bodies.BodyPropertiesOwner;
import bodies.BodyPropertyDefinitions;
import bodies.Physical;
import framework.SimulationType;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleablePropertyFactory;
import javafx.scene.layout.Pane;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import shapes.PhysicsShape;
import utilites.PhysicsShapeHelper;

import java.util.List;

public class ShapeComposition extends Pane implements BodyPropertiesOwner, Physical, PhysicsShape {

    private final BodyPropertyDefinitions<ShapeComposition> bodyPropertyDefinitions;

    private Body body;
    private PhysicsShapeHelper helper;
    private Vec2 localCenterOffset = new Vec2();

    public ShapeComposition() {
        super();
        getStyleClass().add("shapeComposition");
        this.bodyPropertyDefinitions = new BodyPropertyDefinitions<>(this, SPF);
    }

    private static final StyleablePropertyFactory<ShapeComposition> SPF = new StyleablePropertyFactory<>(Pane.getClassCssMetaData());

    @Override
    public BodyPropertyDefinitions<? extends Styleable> getBodyPropertyDefinitions() {
        return bodyPropertyDefinitions;
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return SPF.getCssMetaData();
    }


    @Override
    public void setup(Body body, PhysicsShapeHelper helper){
        this.body = body;
        this.helper = helper;
    }

    @Override
    public void applyForce(float vx, float vy) {
        helper.applyForce(body, localCenterOffset, vx, vy);
    }

    @Override
    public void applyForceUp(float vx, float vy) {
        helper.applyForceUp(body, localCenterOffset, vx, vy);
    }

    @Override
    public ObservableValue<SimulationType> bodyTypeProperty() {
        return bodyPropertyDefinitions.bodyTypeProperty();
    }

    @Override
    public SimulationType getBodyType() {
        return bodyPropertyDefinitions.getBodyType();
    }

    @Override
    public void setBodyType(SimulationType bodyType) {
        bodyPropertyDefinitions.setBodyType(bodyType);
    }

    @Override
    public ObservableValue<Double> linearDampingProperty() {
        return bodyPropertyDefinitions.linearDampingProperty();
    }

    @Override
    public double getLinearDamping() {
        return bodyPropertyDefinitions.getLinearDamping();
    }

    @Override
    public void setLinearDamping(double linearDamping) {
        bodyPropertyDefinitions.setLinearDamping(linearDamping);
    }

    @Override
    public ObservableValue<Double> linearVelocityXProperty() {
        return bodyPropertyDefinitions.linearVelocityXProperty();
    }

    @Override
    public double getLinearVelocityX() {
        return bodyPropertyDefinitions.getLinearVelocityX();
    }

    @Override
    public void setLinearVelocityX(double linearVelocityX) {
        bodyPropertyDefinitions.setLinearVelocityX(linearVelocityX);
    }

    @Override
    public ObservableValue<Double> linearVelocityYProperty() {
        return bodyPropertyDefinitions.linearVelocityYProperty();
    }

    @Override
    public double getLinearVelocityY() {
        return bodyPropertyDefinitions.getLinearVelocityY();
    }

    @Override
    public void setLinearVelocityY(double linearVelocityY) {
        bodyPropertyDefinitions.setLinearVelocityY(linearVelocityY);
    }

    @Override
    public ObservableValue<Double> angularVelocityProperty() {
        return bodyPropertyDefinitions.angularVelocityProperty();
    }

    @Override
    public double getAngularVelocity() {
        return bodyPropertyDefinitions.getAngularVelocity();
    }

    @Override
    public void setAngularVelocity(double angularVelocity) {
        bodyPropertyDefinitions.setAngularVelocity(angularVelocity);
    }

    @Override
    public ObservableValue<Double> angularDampingProperty() {
        return bodyPropertyDefinitions.angularDampingProperty();
    }

    @Override
    public double getAngularDamping() {
        return bodyPropertyDefinitions.getAngularDamping();
    }

    @Override
    public void setAngularDamping(double angularDamping) {
        bodyPropertyDefinitions.setAngularDamping(angularDamping);
    }

    @Override
    public ObservableValue<Double> gravityScaleProperty() {
        return bodyPropertyDefinitions.gravityScaleProperty();
    }

    @Override
    public double getGravityScale() {
        return bodyPropertyDefinitions.getGravityScale();
    }

    @Override
    public void setGravityScale(double gravityScale) {
        bodyPropertyDefinitions.setGravityScale(gravityScale);
    }

    @Override
    public ObservableValue<Boolean> allowSleepProperty() {
        return bodyPropertyDefinitions.allowSleepProperty();
    }

    @Override
    public boolean isAllowSleep() {
        return bodyPropertyDefinitions.isAllowSleep();
    }

    @Override
    public void setAllowSleep(boolean allowSleep) {
        bodyPropertyDefinitions.setAllowSleep(allowSleep);
    }

    @Override
    public ObservableValue<Boolean> awakeProperty() {
        return bodyPropertyDefinitions.awakeProperty();
    }

    @Override
    public boolean isAwake() {
        return bodyPropertyDefinitions.isAwake();
    }

    @Override
    public void setAwake(boolean awake) {
        bodyPropertyDefinitions.setAwake(awake);
    }

    @Override
    public ObservableValue<Boolean> fixedRotationProperty() {
        return bodyPropertyDefinitions.fixedRotationProperty();
    }

    @Override
    public boolean isFixedRotation() {
        return bodyPropertyDefinitions.isFixedRotation();
    }

    @Override
    public void setFixedRotation(boolean fixedRotation) {
        bodyPropertyDefinitions.setFixedRotation(fixedRotation);
    }

    @Override
    public ObservableValue<Boolean> activeProperty() {
        return bodyPropertyDefinitions.activeProperty();
    }

    @Override
    public boolean isActive() {
        return bodyPropertyDefinitions.isActive();
    }

    @Override
    public void setActive(boolean active) {
        bodyPropertyDefinitions.setActive(active);
    }

    @Override
    public ObservableValue<Boolean> bulletProperty() {
        return bodyPropertyDefinitions.bulletProperty();
    }

    @Override
    public boolean isBullet() {
        return bodyPropertyDefinitions.isBullet();
    }

    @Override
    public void setBullet(boolean bullet) {
        bodyPropertyDefinitions.setBullet(bullet);
    }
}