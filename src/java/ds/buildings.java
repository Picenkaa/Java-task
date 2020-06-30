package ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class buildings implements Serializable{
    private String address, owner, property_type;
    private int market_value,size;
  
    
    buildings(String address, String owner,int size, int market_value, String property_type){
        this.address=address;
        this.owner = owner;
        this.size = size;
        this.market_value=market_value;
        this.property_type=property_type;
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public int getMarket_value() {
        return market_value;
    }

    public void setMarket_value(int market_value) {
        this.market_value = market_value;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
//----------------------------------------
    
    
//--------------------------------------------------
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.owner);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final buildings other = (buildings) obj;
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }
//-------------------------------------

    @Override
    public String toString() {
        return "buildings = {" +'"'+ "address"+'"'+":" +'"'+address +'"'+','+'"'+ "owner"+'"'+":" +'"'+owner +'"'+','+'"'+ "property_type"+'"'+":" +'"'+property_type +'"'+','+'"'+ "market_value"+'"'+":"+market_value +','+'"'+ "size"+'"'+":"+size + '}';
    }
   
    
}
