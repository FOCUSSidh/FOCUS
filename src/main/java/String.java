public class String {

	public static void main(java.lang.String[] args) {
		// TODO Auto-generated method stub
int i=2;
int j=1;
		java.lang.String xpath = "//div[@id='emiresults']/table/tbody/tr";
		java.lang.String xpath1=xpath+"["+ (i+1) +"]"+"/td["+(j+1)+"]";
		System.out.println(xpath1);
		
		
		
	}

}
