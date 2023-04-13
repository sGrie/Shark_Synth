import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.security.Key;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXMLLoader;

public class javfxTest extends Application{
    double clickCount = 0.0;
    Button button;
    Label lblCount;
    BorderPane pane;
    Scene scene;
    Button A, Bb, B, C, Db, D, Eb, E, F, Gb, G, Ab, end, octUp, octDown;
    final double semiTone = Math.pow(2, 1/12);
    jsynMess sound = new jsynMess();
    int oct = 3;
    Label octaveShow;
    Slider volume = new Slider(0, 100, 50);
    double amp = .5;


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        sound.running();
        
        A = new Button("A");
        Bb = new Button("Bb");
        B = new Button("B");
        C = new Button("C");
        Db = new Button("Db");
        D = new Button("D");
        Eb = new Button("Eb");
        E = new Button("E");
        F = new Button("F");
        Gb = new Button("Gb");
        G = new Button("G");
        Ab = new Button("Ab");
        end = new Button("Kill");
        octUp = new Button("Octave Up");
        octDown = new Button("Octave Down");
        octaveShow = new Label("Octave " + (oct + 1));

        Label sliderNum = new Label();
        
        HBox whiteKeys = new HBox(C, D, E, F, G, A, B);
        HBox blackKeys = new HBox(Db, Eb, Gb, Ab, Bb);
        HBox octaves = new HBox(10);
        octaves.getChildren().addAll(octDown, octaveShow, octUp, sliderNum);
        
        //bpm input
        TextField bpm = new TextField();
        
        
        
        //volume slider
        volume.setShowTickMarks(true);
        volume.setShowTickLabels(true);
        volume.setMajorTickUnit(25f);
        volume.setOrientation(Orientation.VERTICAL);
        volume.valueProperty().addListener(
             new ChangeListener<Number>() {
 
            public void changed(ObservableValue <? extends Number >
                      observable, Number oldValue, Number newValue)
            {
                int hold = (int)(volume.getValue());
                sliderNum.setText("volume: " + hold + "%");
                amp =  Math.pow((double)newValue, 3) / 1000000;
                
            }
        });

        //play button
        button = new Button();
        button.setText("Play");
        button.setMaxSize(100, 50);
        button.setMinSize(100, 50);
        lblCount = new Label();

        //make lay
        pane = new BorderPane();
        
        pane.setCenter(whiteKeys);
        pane.setTop(blackKeys);
        pane.setRight(volume);
        //pane.setBottom(end);
        pane.setBottom(octaves);
        pane.setStyle("-fx-background-color: LightPink");
        

  
        scene = new Scene(pane, 400, 300);

        //function
        // A.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // Bb.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // B.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // C.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // Db.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // D.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // Eb.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // E.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // F.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // Gb.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // G.addEventHandler(KeyEvent.KEY_RELEASED, strike);
        // Ab.addEventHandler(KeyEvent.KEY_RELEASED, strike);


        // A.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // Bb.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // B.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // C.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // Db.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // D.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // Eb.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // E.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // F.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // Gb.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // G.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // Ab.addEventHandler(KeyEvent.KEY_RELEASED, close);
        // end.addEventHandler(KeyEvent.KEY_RELEASED, close);
        octUp.setOnAction(up);
        octDown.setOnAction(down);

        A.setStyle("-fx-background-color: LightGrey");
        Bb.setStyle("-fx-background-color: LightGrey");
        B.setStyle("-fx-background-color: LightGrey");
        C.setStyle("-fx-background-color: LightGrey");
        Db.setStyle("-fx-background-color: LightGrey");
        D.setStyle("-fx-background-color: LightGrey");
        Eb.setStyle("-fx-background-color: LightGrey");
        E.setStyle("-fx-background-color: LightGrey");
        F.setStyle("-fx-background-color: LightGrey");
        Gb.setStyle("-fx-background-color: LightGrey");
        G.setStyle("-fx-background-color: LightGrey");
        Ab.setStyle("-fx-background-color: LightGrey");



        // C.addEventHandler(KeyEvent.KEY_PRESSED, strike);
        // C.addEventHandler(KeyEvent.KEY_RELEASED, release);

        // Db.addEventHandler(KeyEvent.KEY_PRESSED, strike);
        // Db.addEventHandler(KeyEvent.KEY_RELEASED, release);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case Q: sound.test(	32.70 * Math.pow(2, oct), amp); //C
                    C.setStyle("-fx-background-color: LightCoral");
                    break;
                case DIGIT2: sound.test(34.65 * Math.pow(2, oct), amp); //Db
                    Db.setStyle("-fx-background-color: LightCoral");
                    break;
                case W: sound.test(36.71 * Math.pow(2, oct), amp); //D
                    D.setStyle("-fx-background-color: LightCoral");
                    break;
                case DIGIT3: sound.test(38.89 * Math.pow(2, oct), amp); //Eb
                    Eb.setStyle("-fx-background-color: LightCoral");
                    break;
                case E: sound.test(41.20 * Math.pow(2, oct), amp); //E
                    E.setStyle("-fx-background-color: LightCoral");
                    break;
                case R: sound.test(43.65 * Math.pow(2, oct), amp); //F
                    F.setStyle("-fx-background-color: LightCoral");
                    break;
                case DIGIT5: sound.test(46.25 * Math.pow(2, oct), amp); //Gb
                    Gb.setStyle("-fx-background-color: LightCoral");
                    break;
                case T: sound.test(49.00 * Math.pow(2, oct), amp); //G
                    G.setStyle("-fx-background-color: LightCoral");
                    break;
                case DIGIT6: sound.test(51.91 * Math.pow(2, oct), amp); //Ab
                    Ab.setStyle("-fx-background-color: LightCoral");
                    break;
                case Y: sound.test(55.00 * Math.pow(2, oct), amp); //A
                    A.setStyle("-fx-background-color: LightCoral");
                    break;
                case DIGIT7: sound.test(58.27 * Math.pow(2, oct), amp); //Bb
                    Bb.setStyle("-fx-background-color: LightCoral");
                    break;
                case U: sound.test(61.74 * Math.pow(2, oct), amp); //B
                    B.setStyle("-fx-background-color: LightCoral");
                    break;
            }
            // if (e.getCode() == KeyCode.Q) {
            //     sound.test(261.63 * oct, .1);
            // }
        });

        scene.setOnKeyReleased(e -> {
            sound.stop();
            A.setStyle("-fx-background-color: LightGrey");
            Bb.setStyle("-fx-background-color: LightGrey");
            B.setStyle("-fx-background-color: LightGrey");
            C.setStyle("-fx-background-color: LightGrey");
            Db.setStyle("-fx-background-color: LightGrey");
            D.setStyle("-fx-background-color: LightGrey");
            Eb.setStyle("-fx-background-color: LightGrey");
            E.setStyle("-fx-background-color: LightGrey");
            F.setStyle("-fx-background-color: LightGrey");
            Gb.setStyle("-fx-background-color: LightGrey");
            G.setStyle("-fx-background-color: LightGrey");
            Ab.setStyle("-fx-background-color: LightGrey");
        });

        
        //make stage
       
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });


    }

    // EventHandler noteHitA = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(220.0 * oct);
    //     }
        
    // };
    // EventHandler noteHitBb = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(233.08 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitB = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(246.94 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitC = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(261.63 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitDb = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(277.18 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitD = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(293.66 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitEb = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(311.13 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitE = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(329.63 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitF = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(349.23 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitGb = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(369.99 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitG = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(392.00 * oct, .1);
    //     }
        
    // };
    // EventHandler noteHitAb = new EventHandler<ActionEvent>() {

    //     @Override
    //     public void handle(ActionEvent arg0) {
    //         sound.test(415.30 * oct, .1);
    //     }
        
    // };
    EventHandler close = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            sound.stop();
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");
            B.setStyle("-fx-background-color: LightCoral");

        }
        
    };

    
    // EventHandler strike = new EventHandler<KeyEvent>() {

    //         @Override
    //         public void handle(KeyEvent e) {
    //             switch (e.getCode()){
    //                 case Q: sound.test(	32.70 * Math.pow(2, oct), amp); //C
    //                     break;
    //                 case DIGIT2: sound.test(34.65 * Math.pow(2, oct), amp); //Db
    //                     break;
    //                 case W: sound.test(36.71 * Math.pow(2, oct), amp); //D
    //                     break;
    //                 case DIGIT3: sound.test(38.89 * Math.pow(2, oct), amp); //Eb
    //                     break;
    //                 case E: sound.test(41.20 * Math.pow(2, oct), amp); //E
    //                     break;
    //                 case R: sound.test(43.65 * Math.pow(2, oct), amp); //F
    //                     break;
    //                 case DIGIT5: sound.test(46.25 * Math.pow(2, oct), amp); //Gb
    //                     break;
    //                 case T: sound.test(49.00 * Math.pow(2, oct), amp); //G
    //                     break;
    //                 case DIGIT6: sound.test(51.91 * Math.pow(2, oct), amp); //Ab
    //                     break;
    //                 case Y: sound.test(55.00 * Math.pow(2, oct), amp); //A
    //                     break;
    //                 case DIGIT7: sound.test(58.27 * Math.pow(2, oct), amp); //Bb
    //                     break;
    //                 case U: sound.test(61.74 * Math.pow(2, oct), amp); //B
    //                     break;
    //             }
    //         }   
    // };

    EventHandler release = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent e) {
            sound.stop();
        }   
    };

    EventHandler up = new EventHandler<Event>() {

        @Override
        public void handle(Event arg0) {
            octaveUp();
        }
        
    };

    EventHandler down = new EventHandler<Event>() {

        @Override
        public void handle(Event arg0) {
            octaveDown();
        }
        
    };

    public void octaveUp(){
        oct += 1;
        octaveShow.setText("Octave " + (oct + 1));
    }
    
    public void octaveDown(){
        oct -= 1;
        octaveShow.setText("Octave " + (oct + 1));
    }

    // public void ClickButtonMethod(){
    //     clickCount++;
    //     button.setText(clickCount + "");

        
        
    // }

    
}