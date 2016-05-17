import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hongjiayong on 16/4/29.
 */
public class visitor {
    private ArrayList<Integer> playedPoint;
    private boolean [] unPlayedPoint;
    private int currentPos;
    private int inTime;
    private int outTime;
    public double value;
    private int decideTime;
    private FileWriter writer;
    private int to;
    private boolean exit;
    public Integer [] waitTime = new Integer[11];
    visitor(int inTime, FileWriter writer){
        currentPos = 0;
        playedPoint = new ArrayList<Integer>();
        unPlayedPoint = new boolean[11];
        outTime = -1;
        value = 0.0;
        decideTime = -1;
        exit = false;
        this.inTime = inTime;
        this.writer = writer;

        for (int i = 0; i < 11; i++){
            unPlayedPoint[i] = true;
            waitTime[i] = 0;
        }
    }

    public void setOutTime(int outTime) throws IOException {
        this.outTime = outTime;
    }


    public void next() throws IOException {
        if(playedPoint.size() == 11){
            int chuqu = (int) (outTime + youleyuan.minRoad[currentPos][0] / 1.5 - inTime);
            for (int i = 1; i <= 10; i++){
                writer.write(waitTime[i] + " ");
            }
            writer.write(chuqu + "\n");
            writer.flush();
            youleyuan.youkeNum++;
            exit = true;
            return;
        }
        ArrayList<Integer> can = new ArrayList<Integer>();
        for (int i = 0; i < 11; i++){
            if(unPlayedPoint[i] && youleyuan.minRoad[currentPos][i] <= 800
                    && youleyuan.points.get(i).waitList.size() <= youleyuan.points.get(i).warnNum){
                can.add(i);
            }
        }
        int num = (int) (Math.random() * can.size());
        if(num >= 0 && can.size() != 0) {
            int choose = can.get(num);
            decideTime = youleyuan.staticTime;
            to = choose;
            value = youleyuan.minRoad[currentPos][to] / 1.5;
        }
        else{
            can.clear();
            for (int i = 0; i < 11; i++){
                if(unPlayedPoint[i]){
                    can.add(i);
                }
            }
            System.out.println(can.size());
            int choose = can.get((int) (Math.random() * can.size()));
            decideTime = youleyuan.staticTime;
            to = choose;
            value = youleyuan.minRoad[currentPos][to] / 1.5;
        }
    }

    public void addPlayed(int pos){
        playedPoint.add(pos);
    }

    public double getValue(){
        return value;
    }

    public int getTo(){
        return to;
    }

    public void setTo(int to){
        this.to = to;
    }


    public int getDecideTime(){
        return decideTime;
    }

    public void setCurrentPos(int pos){
        currentPos = pos;
    }

    public boolean getExit() {
        return exit;
    }

    public void setUnPlayedPoint(int pos){
        unPlayedPoint[pos] = false;
    }
}
