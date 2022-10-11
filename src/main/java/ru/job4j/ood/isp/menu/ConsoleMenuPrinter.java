package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    public static final String IDENT = " ";

    @Override
    public String print(Menu menu) {
        StringBuilder stringMenu = new StringBuilder();
        menu.forEach(item -> {
            int num = item.getNumber().split("\\.").length - 1;
            StringBuilder str = new StringBuilder();
            str.append(IDENT.repeat(num));
            System.out.println(str
                    .append(item.getNumber())
                    .append(item.getName()));
            stringMenu.append(str).append(System.lineSeparator());
        });
        return stringMenu.toString();
    }
}
