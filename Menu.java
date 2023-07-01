import javax.swing.JOptionPane;

public class Menu {

    public void run() {
        Model model = new Model();
        int selection = Integer.parseInt(JOptionPane.showInputDialog(null, """
                Выбор пункта меню:
                        1.Поиграть в игру
                        2.Добавить игрушку
                        3.Изменить вес игрушки
                        4.Получить игрушки""",
                "Командное окно",
                JOptionPane.QUESTION_MESSAGE));

        if (selection == 1) {
            model.playLot();
        } else if (selection == 2) {
            model.addToy();
        } else if (selection == 3) {
            model.changeWightChance();
        } else if (selection == 4) {
            model.getToy();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Что-то пошло не так",
                    "Выявлена ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}