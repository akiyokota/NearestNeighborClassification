import java.util.List;
import java.util.Scanner;


public class MainApp {
	
	public static String testFile = "./sample.txt";
	public static int algorithm = 0;
	public static classifierUtilities utility = new classifierUtilities();
	
	public static String getUserInput() {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		return reader.nextLine();
	}
	
	public static void start() {
		System.out.println("Welcome to Akiyo Yokota's Feature Selection Algorithm.");
		System.out.println("Type in the name of the file to text : ");
		testFile = getUserInput();
		
		System.out.println("Type the number of the algorithm you want to run.");
		System.out.println("1) Forward Selection");
		System.out.println("2) Backward Elimination");
		System.out.println("3) Akiyo's Special Algorithm.");
		algorithm = Integer.parseInt(getUserInput());
		
		String fileContent = utility.readFile(testFile);
		List<String> tokens = utility.TokenizeToListByLine(fileContent);
		//utility.printList2(tokens);
		
		System.out.println(testFile);
		System.out.println(algorithm);
	}
	
	public static void test() {
		String fileContent = utility.readFile("sample.txt");
//		NearestNeighborClassifier classifier = new NearestNeighborClassifier(utility.getFeatureMap(utility.TokenizeToListByLine(fileContent)));
//		//System.out.println(classifier.getNumInstances());
//		//classifier.oneDimentionEvaluation(classifier.extractFeatureSet(5));
//		//utility.printAllFeatureMap(classifier.extractAllFeatureSet());
//		//System.out.println(classifier.findBestFeature());
	}
	
	
	public static void test2(){
		String fileContent = utility.readFile("sample.txt");
		//utility.printFeatureMap(utility.getFeatureMap(fileContent));
		NearestNeighborClassifier classifier = new NearestNeighborClassifier(utility.getFeatureMap(fileContent));
		for(int i = 1; i< (classifier.getNumFeatures()+1); i++)
			System.out.println(classifier.getFeatureScore1D(i));

	}
	public static void main(String[] args) {
		//NearestNeighborClassifier classifier = new NearestNeighborClassifier();
		
		test2();
		
		//start();
		//System.out.println(utility.readFile("./sample.txt"));
	}
}
