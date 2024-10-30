class SumaTotal{

	public static int sumarNumeros(int s, int e, int[] array){
		if ( s == e ) return array[s];
		int mitad = (s + e)/2;
		return sumarNumeros(s,  mitad, array) +
		       sumarNumeros(mitad+1,e, array);		
	}

	public static void main(String args[]){
		int array_prueba[] = {1,2,10,34,8};
		System.out.println(sumarNumeros(0, array_prueba.length-1, array_prueba));
		array_prueba = new int[] {11, 22};
		System.out.println(sumarNumeros(0, array_prueba.length-1, array_prueba));
		array_prueba = new int[] {1, 2, 3, 4, 5, -12};
		System.out.println(sumarNumeros(0, array_prueba.length-1, array_prueba));
	}

}
