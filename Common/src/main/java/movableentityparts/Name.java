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
        private String theNameString;
    
    public void setNameToPlayer(){
        theNameString = "player";
    }
    
    public void setNameToFlail(){
        theNameString = "flail";
    }
    
    public void setNameToSlime(){
        theNameString = "slime";
    }
    
    public void setNameToSpaceSlime(){
        theNameString = "spaceSlime";
    }
    
    public void setNameToFireball(){
        theNameString = "fireball";
    }
    
    public void setNameToWall(){
        theNameString = "wall";
    }
    
    public String getName(){
        return theNameString;
    }
    
    public boolean equalsPlayer(){
        if(theNameString.equals("player")){
            return true;
        }
        return false;
    }
    
    public boolean equalsFlail(){
        if(theNameString.equals("flail")){
            return true;
        }
        return false;
    }
    
    public boolean equalsFireball(){
        if(theNameString.equals("fireball")){
            return true;
        }
        return false;
    }
    
    public boolean equalsSlime(){
        if(theNameString.equals("slime")){
            return true;
        }
        return false;
    }
    
    public boolean equalsSpaceSlime(){
        if(theNameString.equals("spaceSlime")){
            return true;
        }
        return false;
    }
    
    public boolean equalsWall(){
        if(theNameString.equals("wall")){
            return true;
        }
        return false;
    }
}
