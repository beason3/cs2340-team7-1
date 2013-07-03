package edu.gatech.cs2340.todo.model;

import java.util.*;

public class Player implements Comparable<Player>
{
    
    String name;
    String country;
    String color;
    int turn;
    TreeMap<String,Integer> occupiedTerritories; //A map of all territories occupied by this player, since multiple units can occupy the same territory
    												//the territory will be the key, the number of units will be the stored value
    											//is this even needed?
//	ArrayList<Resource> ownedResources; //A List of all resources owned by the player
    TreeMap<Integer,Unit> army; //key is Unit ID, value is the actual Unit
    boolean hasLost;
    int[] homebase;

    public Player(String title, String task) { //constructors may be temporary, may later intialize with x army, y turn, etc. 
        name = title;
        country = task;
        turn = 0;  
        army = new TreeMap<Integer,Unit>();
        occupiedTerritories= new TreeMap<String,Integer>();
        hasLost = false;
        homebase = new int[2];
    }
    public int compareTo(Player other){
    	if(turn < other.getTurn())
    		return -1;
    	return 1;
    }
    public boolean equals(Player other){
    	if (name.equals(other.getName()))
    		return true;
    	return false;
    		
    }
    public void setHomeBase(int a, int b)
    {
    	homebase[0] = a;
    	homebase[1] = b;
    }
    public int[] getHomebaseCoords()
    {
    	return homebase;
    }
    public void setName(String title) {
        name = title;
    }
    public String getName() {
        return name;
    }
    public void setCountry(String task) {
        country = task;
    }
    public String getCountry() {
    	return country;
    }
    public void setTurn(int a){
    	turn = a;
    }
    public int getTurn(){
    	return turn;
    }
    public void addUnit(Unit unit) 
    {	
    	army.put(unit.getID(),unit);	
    	
    	String terr = unit.getTerritory().getName();
    	if(occupiedTerritories.containsKey(terr))
    	{
    		int currUnitAmt = occupiedTerritories.get(terr);
    		occupiedTerritories.put(terr, currUnitAmt+1);
    	}
    	else
    		occupiedTerritories.put(terr,1);
    	
    //	update(unit.getID());
    }
    public TreeMap<Integer,Unit> getArmy()
    {
    	return army;
    }
    public int getArmySize() 
    {
    	return army.size();
    }
    public TreeMap<String,Integer> getOccupiedTerritories()
    {
    	return occupiedTerritories;
    }
    
    //cleans up army and occupiedTerritories (gets rid of destroyed units and shifts occupiedTerritories as units move
    public void update()
    {
    	//probably a more elegant way to do this with iterator
    	/*ArrayList<Integer> toBeRemoved = new ArrayList<Integer>();
    	for(int id: army.keySet())
    	{
    		if(army.get(id).getHealth() <= 0)
    		{
    			int currentUnitAmount = occupiedTerritories.get(army.get(id).getTerritory());
    	    	occupiedTerritories.put(army.get(id).getTerritory(),currentUnitAmount-1);
    	    	toBeRemoved.add(id);
    		}
    	}
    	for(int id: toBeRemoved)
    	{
    		army.remove(id);
    	}*/
    }
    public void update(int id)
    {
    	//updates occupiedTerritories, adds Territories that owned units currently occupy to the Treemap, 
    	//then removes no longer occupied territories.
      	//if unit stays in one place, MUST call unit.setTerritory to itself (in Unit)
   /* 		Unit unit = army.get(id);
      		Territory prev = unit.getPreviouslyOccupied();
    		Territory territory = unit.getTerritory();
    		if(occupiedTerritories.keySet().contains(territory))
    		{
    			int currentUnitAmount = occupiedTerritories.get(territory);
    			occupiedTerritories.put(territory,currentUnitAmount+1);
    		}
    		else
    			occupiedTerritories.put(territory,1);
    		
   		if(occupiedTerritories.keySet().contains(prev))
    		{
  			int currentUnitAmount = occupiedTerritories.get(prev);
    			occupiedTerritories.put(prev,currentUnitAmount-1);
    		}
    	
      	
    	//probably a more elegant way to do this with iterator
     	ArrayList<Territory> toBeRemovedT = new ArrayList<Territory>();
     
     	for(Territory a:occupiedTerritories.keySet())
      	{
     	
      		if(occupiedTerritories.get(a) <= 0) //shouldn't ever be less than 0
      		{
      			System.out.println(occupiedTerritories.get(a));
      			toBeRemovedT.add(a);
      		}
     	}
      	for(Territory a: toBeRemovedT)
      	{
     		occupiedTerritories.remove(a);
     	}*/
    }
    public void loses()
    {
    	hasLost = true;
    	army = new TreeMap<Integer,Unit>();
    	occupiedTerritories = new TreeMap<String,Integer>();
    }
    public String toString() {	
    	return name +" from "+country+" has an army with " +army.size()+" units in it and goes on turn "+turn+"\n\n";
    }
    
}
