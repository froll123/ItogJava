import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Model implements ProFunct {
@Override
    public void playLot(){
        Random random=new Random();
        double forIdUser=random.nextDouble()*1880;

        int idUser=(int)forIdUser;

        String str;
        final double[] totalDropRate = {0};
        try {
            File path2=new File("TOY.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(path2,true));

            File path=new File("TOYHOUSE.txt");
            BufferedReader br=new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray =new ArrayList<>();
            while ((str=br.readLine())!=null){
                priorityArray.add(str);
            }


            priorityArray.removeIf(s -> {
                String[] secondArray = s.split(";");
                if (Integer.parseInt(secondArray[3]) == 0) {
                    return true;
                } else {
                    totalDropRate[0] += Double.parseDouble(secondArray[2]);
                    return false;
                }
            });

            double randomValue=random.nextDouble()* totalDropRate[0];
            System.out.println(randomValue);
            double currentSum=0;

            for (int k=0;k<priorityArray.size();k++){
                String[] secondArray=new String[4];
                secondArray=priorityArray.get(k).split(";");
                currentSum+=Double.parseDouble(secondArray[2]);

                if (Integer.parseInt(secondArray[3])!=0){
                    if (randomValue<=currentSum){

                        priorityArray.set(k,
                                (secondArray[0]+";"
                                        +secondArray[1]+";"
                                        +(Integer.parseInt(secondArray[2])-1)+";"
                                        +secondArray[3]));

                        JOptionPane.showMessageDialog(null,
                                "Ты победил: "+secondArray[1]+"\nваш выйгрышный код: "+idUser,
                                "Выигрышное окно",
                                JOptionPane.INFORMATION_MESSAGE);

                        bw.write(secondArray[0]+";"+secondArray[1]+";"+idUser);
                        bw.newLine();
                        bw.close();
                        br.close();
                        break;

                    }
                }
            }

            BufferedWriter bwp=new BufferedWriter(new FileWriter(path));
            String replace="";
            for(int p=0;p<priorityArray.size();p++) {
                replace += priorityArray.get(p) + "\n";
            }

            System.out.println(replace);
            bwp.write(replace);
            bwp.close();

        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void changeWightChance() {
        int idToy = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Введите иденцификатор игрушки, чтобы изменить вес: ",
                "Изменение веса игрушки",
                JOptionPane.INFORMATION_MESSAGE));
        String str;
        try {
            File path = new File("TOYHOUSE.txt");
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);
            }
            br.close();

            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[4];
                secondArray = priorityArray.get(j).split(";");
                if (idToy == Integer.parseInt(secondArray[0])) {
                    int weigthChance = Integer.parseInt(JOptionPane.showInputDialog(null,
                            priorityArray.get(j) + "\nВведите новое значение веса в %: ",
                            "Изменение веса игрушки",
                            JOptionPane.INFORMATION_MESSAGE));
                    String res = secondArray[0] + ";" + secondArray[1] + ";" + secondArray[2] +
                            ";" + weigthChance;
                    priorityArray.set(j, res);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Ошибка",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void addToy() {

        Toy toy=new Toy(Integer.parseInt(JOptionPane.showInputDialog(null,
                "Введите id: ",
                "Добавление игрушки",
                JOptionPane.QUESTION_MESSAGE)),
                JOptionPane.showInputDialog(null,
                        "Введите название: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите колечество:",
                        "Добавление игрушек",
                        JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите вес в %: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE)));
        try {
            File path=new File("TOYHOUSE.txt");

            if (!path.exists()){
                path.createNewFile();
            }

            BufferedWriter bw=new BufferedWriter(new FileWriter(path,true));
            bw.write(toy.getAllInfo());
            bw.newLine();

            JOptionPane.showMessageDialog(null,
                    "Toy: \nid: "
                            +toy.getId()+"\nname: "
                            +toy.getToyName()+"\namount: "
                            +toy.getCountOfToy()+"\nweight: "
                            +toy.getWeigthChance()+"\nsuccessfully added",
                    "Сообщение",
                    JOptionPane.INFORMATION_MESSAGE);
            bw.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,e,"ОШИБКА",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void getToy(){
        int idUser = Integer.parseInt(JOptionPane.showInputDialog(null,
        "Введите иденцификатор участника: ",
        "Получение игрушки",
        JOptionPane.INFORMATION_MESSAGE));
        String str;
        try {
            File path = new File("TOY.txt");
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);

            }
            br.close();

            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[3];
                secondArray = priorityArray.get(j).split(";");
                if (idUser == Integer.parseInt(secondArray[2])) {
                    JOptionPane.showMessageDialog(null,
                            "Поздравляем с победой\n" + secondArray[0] + "," +
                                    "" + secondArray[1],
                            "Получение игрушки",
                            JOptionPane.INFORMATION_MESSAGE);

                    priorityArray.remove(j);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Ошибка",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
