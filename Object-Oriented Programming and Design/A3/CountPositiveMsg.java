package miniTwitter;

public class CountPositiveMsg implements VisitInterface {

		private static int posMsg;
		public void visited() {
			posMsg++;		
		}
		
		public int getPosMsg() {
			return posMsg;
		}

}
