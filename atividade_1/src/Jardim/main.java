package Jardim;


 class Main{
  public static void main(String[] args) {

        //Petalas
        Petala petala1 = new Petala();
        petala1.setCorPetala("marrom");

        Petala petala2 = new Petala();
        petala2.setCorPetala("preto");

        Petala petala3 = new Petala();
        petala3.setCorPetala("azul");

        Petala petala4 = new Petala();
        petala4.setCorPetala("vermelho");

        Petala petala5 = new Petala();
        petala5.setCorPetala("rosa");

        Petala petala6 = new Petala();
        petala6.setCorPetala("branco");

        Petala petala7 = new Petala();
        petala7.setCorPetala("verde");

        Petala petala8 = new Petala();
        petala8.setCorPetala("roxo");


        //Flores
        Flor flor1 = new Flor();
        flor1.setPetala1(petala1);
        flor1.setPetala2(petala2);
        
        Flor flor2 = new Flor();
        flor2.setPetala1(petala3);
        flor2.setPetala2(petala4);

        Flor flor3 = new Flor();
        flor3.setPetala1(petala5);
        flor3.setPetala2(petala6);
        
        Flor flor4 = new Flor();
        flor4.setPetala1(petala7);
        flor4.setPetala2(petala8);
        
        Jardim jardim1 = new Jardim();
        jardim1.setFlor1(flor1);
        jardim1.setFlor2(flor2);

        Jardim jardim2 = new Jardim();
        jardim2.setFlor1(flor3);
        jardim2.setFlor2(flor4);

        Pracinha praca = new Pracinha();
        praca.setJardim1(jardim1);
        praca.setJardim2(jardim2);
        
        System.out.println(praca.j1.getFlor1().getPetala1().getCorPetala());
        System.out.println(praca.j1.getFlor1().getPetala2().getCorPetala());
        System.out.println(praca.j1.getFlor2().getPetala1().getCorPetala());
        System.out.println(praca.j1.getFlor2().getPetala2().getCorPetala());
    }
}