import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  {
        // write your code here
        ArrayList<Integer> taszkok = new ArrayList<>();
        String text = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            text = reader.readLine();

            String[] taszkfeltolt = text.split(",");
            for(int i = 0; i<taszkfeltolt.length ;i++){
                taszkok.add(Integer.parseInt(taszkfeltolt[i])) ;
            }

        }
        catch(Exception e){

        }

        int laphibak=0;
        ArrayList<String> abc = new ArrayList<>();
        ArrayList<Integer> fagyi = new ArrayList<>();
        ArrayList<Integer> szam = new ArrayList<>();
        for(int i = 0; i<taszkok.size();i++){
            boolean csillag=false;
            boolean nemvoltlaphiba=false;
            int fagyilesz=-1;
            //System.out.print(taszkok.get(i));

            if(0>taszkok.get(i)){
                //valami történik
                taszkok.set(i, taszkok.get(i)*(-1));
                fagyilesz=0;
            }
            if(abc.size()<3){

                int vanilyen = vanilyen(taszkok.get(i),szam);
                if(vanilyen==-1){
                    if (abc.size()==2){
                        abc.add("C");
                        System.out.print("C");
                    }
                    else if (abc.size()==1){
                        abc.add("B");
                        System.out.print("B");
                    }
                    else if (abc.size()==0){
                        abc.add("A");
                        System.out.print("A");
                    }
                    szam.add(taszkok.get(i));
                    if(fagyilesz!=0){
                        fagyi.add(4); // a kör végén ugy is csökkentem 1el elsőre is
                    }
                    else{
                        fagyi.add(0);
                    }
                }
                else{
                    String temp=abc.get(vanilyen);
                    abc.remove(vanilyen);  //a tömb végére rakom és 0 ra allitom a fagyasztast
                    abc.add(temp);
                    //System.out.print(abc);
                    int tempfagyi=fagyi.get(vanilyen);
                    fagyi.remove(vanilyen);
                    if(fagyilesz!=0){
                        fagyi.add(tempfagyi);
                    }
                    else{
                        fagyi.add(0);
                    }
                    szam.remove(vanilyen);
                    szam.add(taszkok.get(i));
                    //System.out.print(temp+"e,");
                    nemvoltlaphiba=true;
                }

            }
            else if(abc.size()==3){
                int vanilyen = vanilyen(taszkok.get(i),szam);
                if(vanilyen==-1){  //ha ez egy uj szam
                    int hanyadikbet=vanszabad(fagyi);
                    if(hanyadikbet!=-1){
                        String temp=abc.get(hanyadikbet);
                        szam.remove(hanyadikbet);
                        szam.add(taszkok.get(i));
                        abc.remove(hanyadikbet);  //a tömb végére rakom és 4 ra allitom a fagyasztast
                        abc.add(temp);

                        fagyi.remove(hanyadikbet);
                        if(fagyilesz!=0){
                            fagyi.add(4);
                        }
                        else{
                            fagyi.add(0);
                        }

                        System.out.print(temp);
                    }
                    else{
                        csillag=true;
                    }
                }
                else{
                    String temp=abc.get(vanilyen);
                    abc.remove(vanilyen);  //a tömb végére rakom és 0 ra allitom a fagyasztast
                    abc.add(temp);
                    szam.remove(vanilyen);
                    szam.add(taszkok.get(i));
                    int fagyitemp=fagyi.get(vanilyen);
                    fagyi.remove(vanilyen);
                    fagyi.add(fagyitemp);
                    nemvoltlaphiba=true;
                }


            }

            if (csillag==true){
                System.out.print("*");
            }
            if(nemvoltlaphiba==true){
                System.out.print("-");
            }
            else{
                laphibak++;
            }
            for(int j = 0; j<fagyi.size();j++){
                if(fagyi.get(j)!=0){
                    fagyi.set(j,fagyi.get(j)-1);  //csokkentem a fagyasztast
                }
            }
        }


        System.out.println();
        System.out.print(laphibak);
    }

    private static int vanilyen(int mostani,ArrayList<Integer> foglalt) {

        for(int j = 0; j<foglalt.size();j++){
            if(foglalt.get(j)==mostani){
                return j;
            }
        }
        return -1;
    }

    private static int vanszabad(ArrayList<Integer> fagyika) {
        for(int j = 0; j<fagyika.size();j++){
            if(fagyika.get(j)==0){
                return j;
            }
        }
        return -1;
    }
}
