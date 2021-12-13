/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jardim;

/**
 *
 * @author Jonathan 4
 */




public class Flor {
    Petala petala1;
    Petala petala2;
    
    
    public void setPetala1(Petala pet){
        petala1=pet;
    }
    
    public boolean temPetala1(){
        if(petala1==null) {
            return false;
        }
        return true;
    }
      public Petala getPetala1(){
        return petala1;
    }
  
    
    public void setPetala2(Petala pet){
        petala2=pet;
    }
    
    public boolean temPetala2(){
        if(petala2==null) {
            return false;
        }
        return true;
    }
      public Petala getPetala2(){
        return petala2;
    }
}
