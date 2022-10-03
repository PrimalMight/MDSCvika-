import cz.vutbr.bmds.cv01.MyClass;

public class Main {
    public static void main(String[] args) {
        MyClass prvni = new MyClass();
        MyClass druha = new MyClass();
        MyClass treti = null;

        try{
            treti = new MyClass(1, 2, 3, 4, 5, 6);
            prvni.addInteger(22);
            prvni.addInteger(1231);
            prvni.addInteger(111);

            druha.addInteger(24);
            druha.addInteger(27);

            prvni.print();
            druha.print();

        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());

        }

        System.out.println("Pocet vytvorenych trid: " + MyClass.getCount());
        System.out.println("Existuje 4ka ?: " + treti.integerExists(4));
        System.out.println("Soucet prvku prvniho pole: " + prvni.sum());

        MyClass united = MyClass.createUnited(prvni, druha);
        united.print();

        System.out.println(united.toString());
    }
}
