/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqaure_world;

/**
 *
 * @author VH
 */
public class Entity extends TileComponent
{
	protected static final long serialVersionUID = 100;
        protected int x;
        protected int y;
	/**
	 * The Item specific details
	 */
        
        protected float hp_max = 100;
        protected float hp;
        protected float hp_regenRate;
        
        protected float mana_max;
        protected float mana;
        protected float mana_regenRate;
        
        protected float damage_physical;
        protected float damage_magical;
        
        protected float armor_physical;
        protected float armor_magical;
        
        protected float accuracy_physical;
        protected float accuracy_magical;
        
        protected float dexterity_physical; // dodging
        protected float dexterity_magical;
	
	// More needed
	
	/**
	 * The constructor(s)
	 */
	public Entity (String name, int xInit, int yInit)
	{
		super (name);
                x = xInit;
                y = yInit;
                fileName = nameToPathString(name);
		// More needed
	}
	
	public Entity (Entity sourceObject) // This is a copy constructor. I needed it to deepcopy the object.
	{
		super (sourceObject.getName ());
                x=sourceObject.getX();
                y=sourceObject.getY();
                fileName = sourceObject.getFileName();
                setCustomDescription(sourceObject.description);
		
                hp_max = sourceObject.hp_max;
                hp = sourceObject.hp;
                hp_regenRate = sourceObject.hp_regenRate;
                
                mana_max = sourceObject.mana_max;
                mana = sourceObject.mana;
                mana_regenRate = sourceObject.mana_regenRate;
                
                damage_physical = sourceObject.damage_physical;
                damage_magical = sourceObject.damage_magical;
                
                armor_physical = sourceObject.armor_physical;
                armor_magical = sourceObject.armor_magical;
                
                accuracy_physical = sourceObject.accuracy_physical;
                accuracy_magical = sourceObject.accuracy_magical;
                
                
                
	}
	
	/**
	 * The getters
	 */
	
        public int getX () {return x;}
        public int getY () {return y;}
        
        
        
        public float getHp_max () {return hp_max;}
        public float getHp () {return hp;}
        public float getHp_regenRate () {return hp_regenRate;}
        
        public float getMana_max () {return mana_max;}
        public float getMana () {return mana;}
        public float getMana_regenRate () {return mana_regenRate;}
        
        public float getDamage_physical () {return damage_physical;}
        public float getDamage_magical () {return damage_magical;}
        
        public float getArmor_physical () {return armor_physical;}
        public float getArmor_magical () {return armor_magical;}
        
        public float getAccuracy_physical () {return accuracy_physical;}
        public float getAccuracy_magical () {return accuracy_magical;}
        
        public float getDexterity_physical () {return dexterity_physical;} // dodging
        public float getDexterity_magical () {return damage_magical;}
        
        
        
        
	// More needed
	
	/**
	 * The setters
	 */
        public void setX (int newX) {x=newX;}
        public void setY (int newY) {y=newY;}

	// More needed
        
        
        /**
	 * Actions
	 */
        
        public void getAttacked_physical (Entity attacker)
        {
            float atk_randAcc = (float)Math.random() * attacker.getAccuracy_physical();
            float def_randDex = (float)Math.random() * dexterity_physical;
            
            if ((int)(atk_randAcc*10) >= (int)(def_randDex*10)) // Compare value to the first decimal place.
            {
                //hp = 
            }
            
        }
}