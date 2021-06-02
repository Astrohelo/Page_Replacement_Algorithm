package com.company;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Main {
    static int sor=0;
    static String utolso="0";

    static int legkisebbSjf(ArrayList<Task> tasks, int time,ArrayList<Task> secondTasks){
        int index=0;
        int indextorol=0;
        int temp = time;
        int hossz=0;
        String bet="0";
        boolean volt= false;
        for (Task t : tasks){
            if(t.getPrio()==1&&t.getStart()<=temp){
                hossz=t.getLeng();
            }
        }
        for (Task t : tasks){
            if(t.getPrio()==1&&t.getStart()<=temp && t.getLeng()<=hossz) {
                temp=t.getStart();
                hossz=t.getLeng();
                bet = t.getBetu();
                volt=true;
                indextorol=index;
            }
            index++;
        }
        for (int i= time; i<time+hossz; i++){
            for (Task t : tasks){ ///várakozási idő
                if(t.getBetu()!=bet && t.getStart()<=i ) {
                    for (Task e : secondTasks){
                        if(e.getBetu()==t.getBetu()){
                            e.addWaitTime(1);
                        }
                    }
                }
            }
        }
        if (volt){

                System.out.print(bet);
                utolso = bet;

            tasks.remove(indextorol);
            return hossz;
        }
        return 0;
    }
    static int legkisebbRR(ArrayList<Task> tasks, int time,ArrayList<Task> secondTasks,ArrayList<String> fifo){  //az idő előtti legkevesebbet használt legkorábbi betű

        int index=0;
        int indextorol=0;
        int temp = time;
        int use = time; //max annyi lehet ahányadik alkalomnál vagyunk
        int hossz=0;
        int curSor=0;
        String bet="0";
        boolean volt= false;
        //System.out.println(time+"fifo tartalma :");
        for (String f : fifo) {
            //System.out.println(f);
        }
        for (int i= 0; i<=time; i++){
            for (Task t : tasks) {
                if (t.getStart() == i&&t.getPrio()==0) {
                    boolean alreadyinfifo = false;
                    for (String f : fifo) {
                        if (t.getBetu() == f) {
                            alreadyinfifo = true;
                        }
                    }
                    if (alreadyinfifo == false) {
                        fifo.add(t.getBetu());
                        //System.out.println("most ezt addolom:"+time+t.getBetu());
                    }
                }
            }
        }
        if(fifo.isEmpty()==false&&fifo.get(0)!="0"){
            volt=true;

            //System.out.println(time);
        }

        for (Task t : tasks){
            if(fifo.isEmpty()==false && t.getBetu()==fifo.get(0)){

                bet = t.getBetu();
                //System.out.println("ez a betu"+bet+"ennyiedik:"+time);
                //System.out.print("elotte"+bet);
                indextorol=index;
            }
            index++;
        }
        //System.out.print("utana"+bet);
        if (volt==true ){

            boolean csakegy=false;
            for(Task t: tasks){
                if(t.getPrio()==1&&t.getStart()==time+1) {
                    csakegy=true;
                }
            }
            if (csakegy || tasks.get(indextorol).getLeng()==1){//van utána sjf akkor csak 1et menjen vagy már csak 1 hosszu
                tasks.get(indextorol).subLeng(1);
                hossz=1;
            }
            else{
                tasks.get(indextorol).subLeng(2);
                hossz=2;
                for (int i= 0; i<=time+1; i++){
                    for (Task t : tasks) {
                        if (t.getStart() == i &&t.getPrio()==0) {
                            boolean alreadyinfifo = false;
                            for (String f : fifo) {
                                if (t.getBetu() == f) {
                                    alreadyinfifo = true;
                                }
                            }
                            if (alreadyinfifo == false) {
                                fifo.add(t.getBetu());
                                //System.out.println("most ezt addolom:"+time+t.getBetu());
                            }
                        }
                    }
                }

            }
            for (int i= time; i<time+hossz; i++){
                for (Task t : tasks){ ///várakozási idő
                    if(t.getBetu()!=bet && t.getStart()<=i ) {
                        for (Task e : secondTasks){
                            if(e.getBetu()==t.getBetu()){
                                e.addWaitTime(1);
                            }
                        }
                    }
                }
            }

            if(bet!=utolso) {

                    //System.out.print("ezutan");
                System.out.print(bet);
                utolso = bet;

            }

            fifo.remove(0);
            if(tasks.get(indextorol).getLeng()==0){
                tasks.remove(indextorol);

            }
            else{
                fifo.add(bet);
            }
            return hossz;
        }
        return 0;
    }



    public static void main(String[] args)  {
        // write your code here
        ArrayList<Task> taszkok = new ArrayList<Task>();
        ArrayList<Task> masolat = new ArrayList<Task>();
        ArrayList<String> sorrend = new ArrayList<String>();
        String text = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!(text = reader.readLine()).isEmpty()) {

                String[] taszkfeltolt = text.split(",");
                int egy = Integer.parseInt(taszkfeltolt[1]);
                int ketto = Integer.parseInt(taszkfeltolt[2]);
                int harom = Integer.parseInt(taszkfeltolt[3]);
                Task t=new Task(taszkfeltolt[0], egy, ketto, harom);
                t.setSorrend(sor);
                taszkok.add(t);
                masolat.add(t);
                sor++;
            }
        }
        catch(Exception e){

        }

        int futamido=0;
        for (Task t : taszkok){
            futamido+=t.getLeng();
        }
        String prev="";
        for (int i= 0; i<futamido; i+=0){
            int eredmeny=0;
            if(taszkok.size()==0){

                break;
            }
            eredmeny=legkisebbSjf(taszkok,i,masolat);
            if(eredmeny==0){
                eredmeny=legkisebbRR(taszkok,i,masolat,sorrend);
                if(eredmeny==0){
                    i++;
                    futamido++;
                }
            }
            i+=eredmeny;

        }
        System.out.println();
        for (int i= 0; i<futamido; i++){
            for (Task t : masolat){
                if(t.getSorrend()==i){
                    if(i==0){
                        System.out.print(t.getBetu()+":"+t.getWaitTime());
                    }
                    else{
                        System.out.print(","+t.getBetu()+":"+t.getWaitTime());
                    }

                }
            }
        }

    }
}

