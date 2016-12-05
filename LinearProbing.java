package algoritmos;

class KeysValue{
	Integer key;
	String value;
	boolean dead;
	
	KeysValue(Integer keys, String values){
		key = keys;
		value = values;
		dead = false;
	}
}

class Linear{
	
	private KeysValue[] table = new KeysValue[10];
	private int size = 0;
	float LF ;
	int steps = 1;
	
	int size(){
		return size;
	}

	int Hash(Integer key){
		long res = 1929382942;
		
		res = (res*31 + key) % 1000000009;
	
	return (int) res;
	}
	
	void ResizeNRehash(){
		KeysValue[] tmp = new KeysValue[table.length*2];
		
		
		for(int i=0;i<table.length;i++){
			
			if(table[i]!=null && table[i].dead==false){
			int	newPos=Hash(table[i].key)%tmp.length;
				if(tmp[newPos]==null){
					tmp[newPos]=table[i];
					
				}
				else{
					while(tmp[newPos]!=null){
						//System.out.println("pos "+newPos);
						//System.out.println("table["+i+"]key" +table[i].key);
						if(newPos>=tmp.length){
							newPos-=tmp.length;
						}
						
							
						
						
						newPos+=steps;
						
					}
					tmp[newPos]=table[i];
				}
				
			}
		}
		table=tmp;
	}
	
	
	
	String Find (Integer key){
		int pos=Hash(key)%table.length;
		if(table[pos]==null){
			return null;	
		}
		if(table[pos].key.equals(key)&& table[pos].dead==false ){
			return table[pos].value;
			
		}while(table[pos]!=null){
			
			if(pos>=table.length)
				pos-=table.length;
			
			pos+=steps;
			if(table[pos]==null)
				break;
			if(table[pos].key.equals(key)&& table [pos].dead==false){
				return table[pos].value;
			}	
		}
		return null;
		
	}
	
	void Add (Integer key, String value) throws Exception{
		int pos= Hash(key)%table.length;
		LF=(float)size/table.length;
		if(LF>=0.5){
			
			ResizeNRehash();
		}
		if(Find(key)!=null)
			throw new Exception();
		if(table[pos]==null || table[pos].dead==true){
			table[pos]=new KeysValue(key, value);
			size++;
		}
		else{
			while(table[pos]!=null ){
				if(table[pos].dead==true)
					break;
				if (pos>=table.length)
					pos-=table.length;
				
		       pos+=steps;
			
			
			}	
			table[pos]=new KeysValue(key, value);
			size++;
		}
		
	}
	public String Remove(Integer key)throws Exception{
		
		if(size==0)
			throw new Exception();
		int pos=Hash(key)%table.length;
		String ret;
		if (Find(key)==null)
			throw new Exception();
		else{
			if(table[pos].key.equals(key) ){
				ret=table[pos].value;
				table[pos].dead=true;
		}
			else{
				while(true){
					if(pos>=table.length)
						pos-=table.length;
					
					pos+=steps;
					
					if(table[pos].key.equals(key)&& table [pos].dead==false){
						ret= table[pos].value;
						table[pos].dead=true;
						break;
					
					}	
				}
			}
				
			}
		size--;
		return ret;
	}
	
	public void Replace(Integer key,String value) throws Exception{
		int pos=Hash(key)%table.length;
		
		if(Find(key)!=null)
			throw new Exception();
		table[pos]=new KeysValue(key, value);
		
	}
		
		
	
}

public class LinearProbing {

	public static void main(String[] args) throws Exception {
		
		Linear arr = new Linear();
		
		arr.Add(13, "Trece");
		arr.Add(21, "DosUno");
		arr.Add(3, "Dds");
		arr.Add(4, "Ds");
		arr.Add(5, "Des");
		arr.Add(6, "Drs");
		arr.Add(7, "Dys");
		arr.Add(8, "Dus");
		arr.Add(9, "Dhs");
		arr.Remove(7);
		arr.Add(10, "Gos");
		arr.Add(54, "Yo");
		
		System.out.println(arr.Find(13));
		System.out.println(arr.Find(21));
		//System.out.println(arr.Find(3));
		//System.out.println(arr.Find(4));
		//System.out.println(arr.Find(5));
		//System.out.println(arr.Find(6));
		arr.Replace(9, "Dus");
		System.out.println(arr.Find(9));
		//System.out.println(arr.Find(7));
		//System.out.println(arr.Find(8));
		//System.out.println(arr.Find(F9));
		//System.out.println(arr.Find(10));
		//System.out.println(arr.Find(54));
		System.out.println(arr.size());
		
		

	}

}
