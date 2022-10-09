package ru.job4j.ood.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {
    public static final String IDENT = ".";
    private static final int PERIOD_LENGTH = 3;

    @Override
    public String print(Menu menu) {
        StringBuilder stringMenu = new StringBuilder();
        menu.forEach(item -> {
            int num = item.getNumber().length() / PERIOD_LENGTH;
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
