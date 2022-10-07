package ru.job4j.ood.isp.menu;

public class MenuInfoPrint implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(item -> {
            int num = item.getNumber().length() / 3;
            StringBuilder str = new StringBuilder();
            str.append(".".repeat(num));
            System.out.println(str
                    .append(item.getNumber())
                    .append(item.getName()));
        });
    }
}
