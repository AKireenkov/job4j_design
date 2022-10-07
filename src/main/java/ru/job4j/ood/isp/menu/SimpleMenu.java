package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (parentName == null) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            findItem(parentName).get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        var item = findItem(itemName);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("item is empty !");
        }
        ItemInfo itemInfo = item.get();
        return Optional.of(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    public Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        Optional<ItemInfo> item = Optional.empty();
        while (dfsIterator.hasNext()) {
            ItemInfo next = dfsIterator.next();
            if (name.equals(next.menuItem.getName())) {
                item = Optional.of(next);
            }
        }
        return item;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        @Override
        public String toString() {
            return "SimpleMenuItem{"
                    + "name='" + name + '\''
                    + ", children=" + children
                    + ", actionDelegate=" + actionDelegate
                    + '}';
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}