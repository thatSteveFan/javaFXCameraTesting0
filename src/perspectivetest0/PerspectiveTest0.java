/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perspectivetest0;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author pramukh
 */
public class PerspectiveTest0 extends Application
{

    private final boolean DEBUGGING = false;

    public final int ROTATE_AMOUNT = 30;
    private final double SIN_OF_ROTATE_AMOUNT = Math.sin(Math.toRadians(ROTATE_AMOUNT));

    @Override
    public void start(Stage primaryStage)
    {

        Group root = new Group();

        Pane image = panify("https://www.petfinder.com/wp-content/uploads/2012/11/140272627-grooming-needs-senior-cat-632x475.jpg");

        image.setPrefWidth(100);
        image.setPrefHeight(100 * 475 / 632.0);
        image.getTransforms().add(new Rotate(ROTATE_AMOUNT, Rotate.X_AXIS));
        image.setTranslateZ(-100 * 475 / 632.0 * SIN_OF_ROTATE_AMOUNT);
        image.setTranslateX(100);
        image.setTranslateY(100);

        root.getChildren().add(image);

        Pane notreDame = panify("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/NotreDameDeParis.jpg/181px-NotreDameDeParis.jpg");
        notreDame.setPrefWidth(100);
        notreDame.setPrefHeight(300);
        notreDame.getTransforms().add(new Rotate(ROTATE_AMOUNT, Rotate.X_AXIS));
        notreDame.translateZProperty().bind(notreDame.heightProperty().negate().multiply(SIN_OF_ROTATE_AMOUNT));
        notreDame.setTranslateX(100);
        notreDame.setTranslateY(100);

        root.getChildren().add(notreDame);

        Scene scene = new Scene(root, 350, 250, true, SceneAntialiasing.BALANCED);
        PerspectiveCamera camera = new PerspectiveCamera();

        camera.setTranslateZ(-100);
        camera.getTransforms().add(new Rotate(ROTATE_AMOUNT, Rotate.X_AXIS));

        scene.setCamera(camera);
        if (DEBUGGING)
        {
            camera.getTransforms().addAll(new Rotate(90, Rotate.Y_AXIS),
                                          new Rotate(60, Rotate.Z_AXIS));
        }

        BooleanProperty upKeyDown = new SimpleBooleanProperty(false);
        BooleanProperty downKeyDown = new SimpleBooleanProperty(false);
        BooleanProperty leftKeyDown = new SimpleBooleanProperty(false);
        BooleanProperty rightKeyDown = new SimpleBooleanProperty(false);

        BooleanProperty wKeyDown = new SimpleBooleanProperty(false);
        BooleanProperty aKeyDown = new SimpleBooleanProperty(false);
        BooleanProperty sKeyDown = new SimpleBooleanProperty(false);
        BooleanProperty dKeyDown = new SimpleBooleanProperty(false);

        scene.onKeyPressedProperty().set(e
                -> 
                {

                    if (e.getCode() == KeyCode.UP)
                    {
                        upKeyDown.setValue(Boolean.TRUE);
                    }
                    if (e.getCode() == KeyCode.LEFT)
                    {
                        leftKeyDown.setValue(Boolean.TRUE);
                    }
                    if (e.getCode() == KeyCode.DOWN)
                    {
                        downKeyDown.setValue(Boolean.TRUE);
                    }
                    if (e.getCode() == KeyCode.RIGHT)
                    {
                        rightKeyDown.setValue(Boolean.TRUE);
                    }

                    if (e.getCode() == KeyCode.W)
                    {
                        wKeyDown.setValue(Boolean.TRUE);
                    }
                    if (e.getCode() == KeyCode.A)
                    {
                        aKeyDown.setValue(Boolean.TRUE);
                    }

                    if (e.getCode() == KeyCode.S)
                    {
                        sKeyDown.setValue(Boolean.TRUE);
                    }

                    if (e.getCode() == KeyCode.D)
                    {
                        dKeyDown.setValue(Boolean.TRUE);
                    }

        });

        scene.onKeyReleasedProperty().set(e
                -> 
                {
                    if (e.getCode() == KeyCode.UP)
                    {
                        upKeyDown.setValue(Boolean.FALSE);
                    }
                    if (e.getCode() == KeyCode.LEFT)
                    {
                        leftKeyDown.setValue(Boolean.FALSE);
                    }
                    if (e.getCode() == KeyCode.DOWN)
                    {
                        downKeyDown.setValue(Boolean.FALSE);
                    }
                    if (e.getCode() == KeyCode.RIGHT)
                    {
                        rightKeyDown.setValue(Boolean.FALSE);
                    }

                    if (e.getCode() == KeyCode.W)
                    {
                        wKeyDown.setValue(Boolean.FALSE);
                    }
                    if (e.getCode() == KeyCode.A)
                    {
                        aKeyDown.setValue(Boolean.FALSE);
                    }

                    if (e.getCode() == KeyCode.S)
                    {
                        sKeyDown.setValue(Boolean.FALSE);
                    }

                    if (e.getCode() == KeyCode.D)
                    {
                        dKeyDown.setValue(Boolean.FALSE);
                    }
        });

        Timeline t = new Timeline(new KeyFrame(new Duration(5), "animation", e -> 
                                               {
                                                   if (upKeyDown.getValue() || wKeyDown.getValue())
                                                   {
                                                       camera.setTranslateY(camera.getTranslateY() - 1);
                                                       image.setTranslateY(image.getTranslateY() - 1);
                                                   }
                                                   if (downKeyDown.getValue() || aKeyDown.getValue())
                                                   {
                                                       camera.setTranslateY(camera.getTranslateY() + 1);
                                                       image.setTranslateY(image.getTranslateY() + 1);
                                                   }
                                                   if (leftKeyDown.getValue() || sKeyDown.getValue())
                                                   {
                                                       camera.setTranslateX(camera.getTranslateX() - 1);
                                                       image.setTranslateX(image.getTranslateX() - 1);
                                                   }
                                                   if (rightKeyDown.getValue() || dKeyDown.getValue())
                                                   {
                                                       camera.setTranslateX(camera.getTranslateX() + 1);
                                                       image.setTranslateX(image.getTranslateX() + 1);
                                                   }

                                       }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        GridPane grid = grid(10);
        grid.setGridLinesVisible(true);

        grid.setMinSize(1000, 1000);
        grid.setBackground(new Background(new BackgroundImage(new Image("http://www.myfreetextures.com/wp-content/uploads/2015/01/grass-free-texture.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        root.getChildren().add(grid);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    public GridPane grid(double size)
    {
        GridPane temp = new GridPane();
        List<ColumnConstraints> cList = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100 / size);
            cList.add(cc);
        }
        temp.getColumnConstraints().addAll(cList);

        List<RowConstraints> rList = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100 / size);
            rList.add(rc);
        }
        temp.getRowConstraints().addAll(rList);

        return temp;
    }

    /**
     * makes a pane out of a image specified in a url.
     *
     * @param url
     * @return
     */
    public Pane panify(String url)
    {
        ImageView img = new ImageView(url);
        img.setSmooth(true);
        return panify(img);
    }

    /**
     * wraps the given ImageView in a Pane
     *
     * @param img
     * @return
     */
    public Pane panify(ImageView img)
    {
        Pane pane = new Pane(img);
        img.fitWidthProperty().bind(pane.widthProperty());
        img.fitHeightProperty().bind(pane.heightProperty());
        return pane;
    }

}
