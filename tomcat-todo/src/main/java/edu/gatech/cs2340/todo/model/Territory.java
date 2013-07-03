package edu.gatech.cs2340.todo.model;

import edu.gatech.cs2340.todo.model.Player;
import edu.gatech.cs2340.todo.model.Resource;
import java.util.*;

public class Territory implements Comparable<Territory>
{
	
	String name; //Name of territory, ie states/countries
	int[] coordinates; //coordinate of Territory, chessboard style. length = 2, just 2 numbers. May be awkward if board is hexes and not squares
	ArrayList<Resource> resources; //Resources this particular territory possesses, ie additional money to purchase troops, upgrades for troops, map information
	boolean isOccupied; //true if there is a unit occupying the territory -> presence enough to occupy or need to spend turns capping?
	TreeMap<Integer,Unit> occupiedByUnit; //key is unit id, value is the unit
	Player occupiedByPlayer; //the player who occupies this territory 
	boolean occupiable;
	boolean homeBase;

public Territory(String name, int[] coords){
	
	this.name=name;
	coordinates = coords;
	resources = new ArrayList<Resource>();
	isOccupied = false;
    occupiedByUnit = new TreeMap<Integer,Unit>();
	occupiedByPlayer = null;
	occupiable = true;
	homeBase = false;
	}
public int compareTo(Territory other){
	if(coordinates[0] < other.getCoords()[0])
		return -1;
	else if(coordinates[0] > other.getCoords()[0])
		return 1;
	//if in same row
	else if(coordinates[1] > other.getCoords()[1])
		return 1;
	else 
		return -1;

}
public boolean equals(Territory other){
	if (Arrays.equals(coordinates,other.getCoords()))
		return true;
	return false;
		
}


	//if the territory is occupied by an asteroid
	public void makeNotOccupiable(){
	    occupiable = false;
	}  
	//the "home base" of the player. New units will spawn adjacent to the home base and if it is "conquered", that player loses.
	public void makeHomeBase(Player player){
	    homeBase = true;
	    occupiedByPlayer = player;
	    player.setHomeBase(coordinates[0],coordinates[1]);
    } 
	public boolean isHomeBase()
	{
		return homeBase;
	}

	public void addResource(Resource treasure)
	{
		resources.add(treasure);
		if (treasure.getName().equals("Asteroid"))
			occupiable = false;
	}
	public boolean hasResources()
	{
		if (resources.size()>0)
			return true;
		else 
			return false;
	}
	
	//player parameter slightly redundant
	public void addUnit(Unit conquerer)
	{
		if(occupiable)// not an asteroid
		{
			//if territory is already occupied by another player, then fight
/*			if(isOccupied && !occupiedByPlayer.equals(conquerer.getOwner())) 
			{
				//fight
			}
			else
			{*/
				isOccupied = true;
				occupiedByUnit.put(conquerer.getID(),conquerer);
				occupiedByPlayer = conquerer.getOwner();
	//		}
		}
		else
			System.out.println("There's something in the way!");
		
		update();
	}
	public String getName(){
		return name;
	}
	public int[] getCoords(){
		return coordinates;
	}

	public Boolean isOccupied()
	{
		return isOccupied;
	}
	public TreeMap<Integer, Unit> getOccupants()
	{
		return occupiedByUnit;
	}
	public Player getPlayer()
	{
		return occupiedByPlayer;
	}
	
	//removes units that no longer occupy this space
	//can probably implement much more elegantly with an iterator
	public void update()
	{
		//if unit is dead
//    	ArrayList<Integer> toBeRemoved = new ArrayList<Integer>();
//    	for(int id: occupiedByUnit.keySet())
//    	{
//    		if(occupiedByUnit.get(id).getHealth() <= 0)
//    		{
//    			toBeRemoved.add(id);
//    		}
//    	}
//    	for(int id: toBeRemoved)
//    	{
//    		occupiedByUnit.remove(id);
//    	}
//    	toBeRemoved = new ArrayList<Integer>();
//		for(int id: occupiedByUnit.keySet())
//		{
//			if(!Arrays.equals(this.getCoords(), occupiedByUnit.get(id).getTerritory().getCoords()))
//			{
//				toBeRemoved.add(id);
//			}
//		}
//		for(int a: toBeRemoved)
//			occupiedByUnit.remove(a);
//		
//		if(occupiedByUnit.size() == 0)
//			occupiedByPlayer = null;
	}
	public String toString()
	{
		return name;
	}
	
	
	
	
	
	
	
}