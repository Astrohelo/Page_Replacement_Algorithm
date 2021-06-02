package com.company;

public class Task {


    private String betu;
    private int prio;
    private int start;
    private int leng;
    private int used;
    private int sorrend;
    private int waitTime;

    public Task(String s, int p, int st,int l){
        this.betu=s;
        this.prio=p;
        this.start = st;
        this.leng = l;
        this.used=0;
        this.sorrend = -1;
        this.waitTime = 0;

    }

    public String getBetu(){return betu;}
    public int getPrio(){return prio;}
    public int getStart(){return start;}
    public int getLeng(){return leng;}
    public int getUsed(){return used;}
    public void Used(){used++;}
    public void subLeng(int l){leng=leng-l;}

    public void setSorrend(int s){sorrend=s;}
    public void addWaitTime(int w){waitTime+=w;}
    public int getSorrend(){return sorrend;}
    public int getWaitTime(){return waitTime;}



}
