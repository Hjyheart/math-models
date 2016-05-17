import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by hongjiayong on 16/4/29.
 */
public class youPoint{
    private  int name;
    private  FileWriter writer;
    public   int goTime;
    private  int runTime;
    public Stack<visitor> waitList;
    private Stack<visitor> inList;
    public ArrayList<visitor> passList;
    private int fullNum;

    // 优化方案
    public int warnNum;

    youPoint(int name, FileWriter writer, int runTime, int fullNum, int warnNum){
        this.name = name;
        this.writer = writer;
        this.runTime = runTime;
        this.fullNum = fullNum;
        this.warnNum = warnNum;

        waitList = new Stack<visitor>();
        inList = new Stack<visitor>();
        passList = new ArrayList<visitor>();
        goTime = 0;
    }

    public void go() throws IOException {
        goTime++;
        try {
                writer.write(waitList.size() + "\n");
                writer.flush();
        } catch (IOException e) {
                e.printStackTrace();
        }

        // 取等待队列
        while (inList.size() < fullNum && !waitList.isEmpty()){
                inList.add(waitList.pop());
        }

        // 取点
//        for (int i = 0; i < 11; i++){
//            for (int k = 0; k < youleyuan.points.get(i).passList.size(); k++){
//                visitor temp = youleyuan.points.get(i).passList.get(k);
//                if(temp.getValue() + temp.getDecideTime() < youleyuan.staticTime && temp.getTo() == name){
//                    temp.setCurrentPos(name);
//                    System.out.println(youleyuan.staticTime + " " + name);
//                    if(inList.size() < fullNum && goTime <= 0.2 * runTime){
//                        inList.add(temp);
//                    }else{
//                        temp.waitTime[name] = waitList.size() / fullNum * runTime;
//                        waitList.add(temp);
//
//                    }
//                    youleyuan.points.get(i).passList.remove(k);
//                    k--;
//                }
//            }
//        }


        for (int i = 0; i < 11; i++){
            for (int k = 0; k < youleyuan.points.get(i).passList.size(); k++){
                visitor temp = youleyuan.points.get(i).passList.get(k);
                if(temp.getValue() + temp.getDecideTime() < youleyuan.staticTime && temp.getTo() == name){
                    System.out.println(youleyuan.staticTime + " " + name);
                    if(inList.size() < fullNum){
                        temp.setCurrentPos(name);
                        inList.add(temp);
                        youleyuan.points.get(i).passList.remove(k);
                        k--;
                    }else{
                        if(waitList.size() <= warnNum) {
                            temp.setCurrentPos(name);
                            temp.waitTime[name] = (waitList.size() / fullNum + 1) * runTime - goTime;
                            waitList.add(temp);
                            youleyuan.points.get(i).passList.remove(k);
                            k--;
                        }else {
                            while (waitList.size() > warnNum) {
                                double choose = Math.random();
                                if (choose <= 0.1) {
                                    temp.setCurrentPos(name);
                                    temp.waitTime[name] = (waitList.size() / fullNum + 1) * runTime - goTime;
                                    waitList.add(temp);
                                    youleyuan.points.get(i).passList.remove(k);
                                    k--;
                                    break;
                                } else {
                                    youleyuan.points.get(i).passList.remove(k);
                                    k--;
                                    temp.setCurrentPos(name);
                                    temp.next();
                                    passList.add(temp);
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }



        if(goTime == runTime){
            goTime = 0;
            for (int i = 0; i < fullNum; i++){
                if(inList.isEmpty()){
                    break;
                }
                visitor temp = inList.pop();
                temp.addPlayed(name);
                temp.setUnPlayedPoint(name);
                try {
                    temp.setOutTime(youleyuan.staticTime);
                    temp.setTo(-1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                temp.next();
                if(!temp.getExit()) {
                    passList.add(temp);
                }
            }
            // 取等待队列
            while (inList.size() < fullNum && !waitList.isEmpty()){
                inList.add(waitList.pop());
            }
        }
    }

    public  void getIn(visitor vis){
        passList.add(vis);
    }

}
