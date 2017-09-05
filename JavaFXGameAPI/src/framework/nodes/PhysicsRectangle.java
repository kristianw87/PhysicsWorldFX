package framework.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bodies.BodyPropertyDefinitions;
import bodies.BodyPropertiesOwner;
import bodies.Physical;
import framework.ChangedEvent;
import framework.ChangedEventListener;
import framework.SimulationType;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.geometry.Point2D;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import javafx.scene.shape.Rectangle;
import shapes.*;
import utilites.PhysicsShapeHelper;

public class PhysicsRectangle extends Rectangle implements SingleShape {

    private Body body;
    private List<ChangedEventListener> sizeChangedListeners;
    private PhysicsShapeHelper helper;
    private Vec2 localCenterOffset = new Vec2();

    private static final StyleablePropertyFactory<PhysicsRectangle> SPF = new StyleablePropertyFactory<>(Rectangle.getClassCssMetaData());
    private FixturePropertyDefinitions<PhysicsRectangle> fixturePropertyDefinitions;
    private BodyPropertyDefinitions<PhysicsRectangle> bodyPropertyDefinitions;

    public PhysicsRectangle() {
        getStyleClass().add("physicsRectangle");
        this.fixturePropertyDefinitions = new FixturePropertyDefinitions<>(this, SPF);
        this.bodyPropertyDefinitions = new BodyPropertyDefinitions<>(this, SPF);
        sizeChangedListeners = new ArrayList<>();
        widthProperty().addListener((observable, oldValue, newValue) -> raiseEvent(sizeChangedListeners));
        heightProperty().addListener((observable, oldValue, newValue) -> raiseEvent(sizeChangedListeners));
    }

    @Override
    public FixturePropertyDefinitions<? extends Styleable> getFixturePropertyDefinitions() {
        return fixturePropertyDefinitions;
    }

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
    public void addSizeChangedEventListener(ChangedEventListener listener)  {
        sizeChangedListeners.add(listener);
    }

    @Override
    public void setLocalCenterOffset(Vec2 vec2) {
        localCenterOffset = vec2;
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

    @Override
    public ObservableValue<Double> densityProperty() {
        return fixturePropertyDefinitions.densityProperty();
    }

    @Override
    public double getDensity() {
        return fixturePropertyDefinitions.getDensity();
    }

    @Override
    public void setDensity(double density) {
        fixturePropertyDefinitions.setDensity(density);
    }

    @Override
    public ObservableValue<Double> frictionProperty() {
        return fixturePropertyDefinitions.frictionProperty();
    }

    @Override
    public double getFriction() {
        return fixturePropertyDefinitions.getFriction();
    }

    @Override
    public void setFriction(double friction) {
        fixturePropertyDefinitions.setFriction(friction);
    }

    @Override
    public ObservableValue<Double> restitutionProperty() {
        return fixturePropertyDefinitions.restitutionProperty();
    }

    @Override
    public double getRestitution() {
        return fixturePropertyDefinitions.getRestitution();
    }

    @Override
    public void setRestitution(double restitution) {
        fixturePropertyDefinitions.setRestitution(restitution);
    }

    @Override
    public ObservableValue<Boolean> sensorProperty() {
        return fixturePropertyDefinitions.sensorProperty();
    }

    @Override
    public boolean isSensor() {
        return fixturePropertyDefinitions.isSensor();
    }

    @Override
    public void setSensor(boolean sensor) {
        fixturePropertyDefinitions.setSensor(sensor);
    }

    @Override
    public ObservableValue<Integer> filterMaskProperty() {
        return fixturePropertyDefinitions.filterMaskProperty();
    }

    @Override
    public int getFilterMask() {
        return fixturePropertyDefinitions.getFilterMask();
    }

    @Override
    public void setFilterMask(int filterMask) {
        fixturePropertyDefinitions.setFilterMask(filterMask);
    }

    @Override
    public ObservableValue<Integer> filterCategoryProperty() {
        return fixturePropertyDefinitions.filterCategoryProperty();
    }

    @Override
    public int getFilterCategory() {
        return fixturePropertyDefinitions.getFilterCategory();
    }

    @Override
    public void setFilterCategory(double filterCategory) {
        fixturePropertyDefinitions.setFilterCategory(filterCategory);
    }

    @Override
    public ObservableValue<Integer> filterGroupProperty() {
        return fixturePropertyDefinitions.filterGroupProperty();
    }

    @Override
    public int getFilterGroup() {
        return fixturePropertyDefinitions.getFilterGroup();
    }

    @Override
    public void setFilterGroup(int filterGroup) {
        fixturePropertyDefinitions.setFilterGroup(filterGroup);
    }

    private void raiseEvent(List<ChangedEventListener> eventListeners){
        ChangedEvent event = new ChangedEvent(this);
        Iterator i = eventListeners.iterator();
        while(i.hasNext())  {
            ((ChangedEventListener) i.next()).handleChangedEvent(event);
        }
    }

}