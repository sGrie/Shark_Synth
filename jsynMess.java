import javafx.event.EventHandler;
import javafx.event.Event;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.LinearRamp;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.softsynth.shared.time.TimeStamp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class jsynMess extends Application{
    Synthesizer synth = JSyn.createSynthesizer();
    SineOscillator sine = new SineOscillator();
    SawtoothOscillator saw = new SawtoothOscillator();
    SquareOscillator square = new SquareOscillator();
    TriangleOscillator triangle = new TriangleOscillator();
    LineOut lineout = new LineOut();
    private UnitOscillator osc;


    
    public void running(){
        
        synth.add(saw);

        synth.add(lineout);

        saw.output.connect(0, lineout.input, 0);
        saw.output.connect(0, lineout.input, 1);
        synth.start();
        saw.noteOff();
        
        synth.startUnit(lineout);

        
    }




    public void test(double freq, double amp){
        
        // double amp = .2;
        //create oscillator
        double timeNow = synth.getCurrentTime();
        TimeStamp timeStamp = new TimeStamp(timeNow);

        saw.noteOn(freq, amp);
        //if(isRealTime())
        //saw.noteOff(timeStamp.makeRelative(length));

        try {
            synth.sleepUntil(timeStamp.getTime());
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //synth.stop();

        


    }
    
    public void constant(double freq){
        synth.add(osc = new SineOscillator());
        synth.add(lineout);

        osc.output.connect(0, lineout.input, 0);


    }
    public void begin(){
        synth.start();
        lineout.start();
    }

    public void stop(){
        // synth.stop();
        // synth.start();
        // lineout.stop();
        saw.noteOff();
    }
    public static void main(String[] args){

    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
