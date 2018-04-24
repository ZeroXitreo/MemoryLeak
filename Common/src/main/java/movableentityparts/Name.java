/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;

/**
 *
 * @author jonas
 */
public class Name {
        private String theName;
    
    public void setNameToPlayer(){
        theName = "player";
    }
    
    public void setNameToSword(){
        theName = "sword";
    }
    
    public void setNameToSlime(){
        theName = "slime";
    }
    
    public void setNameToSpaceSlime(){
        theName = "spaceSlime";
    }
    
    public void setNameToFireball(){
        theName = "fireball";
    }
    
    public void setTypeToWall(){
        theName = "wall";
    }
    
    public String getName(){
        return theName;
    }
}
