import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class classifierUtilities {
	public String readFile(String filename) {
	    try {
	    	StringBuilder content = new StringBuilder();
	    	BufferedReader br = new BufferedReader(new FileReader(filename));
	    	String line = "";
	    	while(( line = br.readLine())!=null) 
	    		content.append(line).append("\n");
	    	br.close();
	    	return content.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	public void printList(List<String> list) {
		for(String s : list) {
			//System.out.println(new BigDecimal(s).doubleValue());
			System.out.println(s);
		}
	}
	
	public void printList2(List<String> list) {
		for(String s: list) {
			printList(TokenizeToListBySpace(s));
			//printList(new BigDecimal(TokenizeToListBySpace(s)).doubleValue());
		}
	}
	
	public List<String> TokenizeToListByLine(String content) {
		List <String> tokens = new LinkedList<String> ();
		
		StringTokenizer st = new StringTokenizer(content, "\n");
		
		while(st.hasMoreElements()) {
			tokens.add(st.nextElement().toString());
		}
		return tokens;
	}
	
	public List<String> TokenizeToListBySpace(String content) {
		List <String> tokens = new LinkedList<String> ();
		
		StringTokenizer st = new StringTokenizer(content, " ");
		
		while(st.hasMoreElements()) {
			tokens.add(st.nextElement().toString());
		}
		return tokens;
	}
	
	public List< Map < Integer, Map< Integer, Double>>>  getFeatureMap(String content) {
		//  List < Map < classNo , Map <featureNo, featureVal> > >
		List< Map < Integer, Map< Integer, Double>>> featureMap = new LinkedList< Map < Integer, Map< Integer, Double>>> ();
		
		List<String> rows = TokenizeToListByLine(content);
		for(String row : rows ) {
			StringTokenizer st = new StringTokenizer(row, " ");
			int classNo = new BigDecimal(st.nextElement().toString()).intValue();
			
			int featureNo = 1;
			Map<Integer, Double> featureSet = new HashMap<Integer, Double> ();
			while(st.hasMoreElements()) {
				featureSet.put(featureNo, new BigDecimal(st.nextElement().toString()).doubleValue());
				featureNo = featureNo + 1;
			}
			Map < Integer, Map< Integer, Double>> newRow = new HashMap < Integer, Map< Integer, Double>>();
			newRow.put(classNo, featureSet);
			featureMap.add(newRow);
		}
		return featureMap;
	}
	
	
	public void printFeatureMap (List< Map < Integer, Map< Integer, Double>>> map) {
		for( Map < Integer, Map< Integer, Double>> row : map) {
			for(Integer classNo : row.keySet()) {
				for (Integer featureNo : row.get(classNo).keySet()) {
					System.out.println("Class No : " + classNo + "\tFeature No : " + featureNo + "\tFeature Val: " + row.get(classNo).get(featureNo));
				}
			}
		}
	}
	
	
//	public void printFeatureMap (Map<Integer, List<Map<Integer, Double>>> map) {
//		System.out.println("hello");
//		for(int classNo : map.keySet()) { //for every class
//			for(Map<Integer, Double> features : map.get(classNo)){ //for each row of features
//				for(int i = 0; features.containsKey(i); i++) {	//features of each column
//					System.out.println("Class NO" + classNo + "\t" + "Feature" + i + " : " + features.get(i));
//				}
//			}
//		}
//	}
//	
//	public Map<Integer, List<Map<Integer, Double>>> getFeatureMap(List<String> data) {		
//		//<class, <feature#, feature Val>>
//		Map<Integer, List<Map<Integer, Double>>> featureMap = new HashMap<Integer, List<Map<Integer, Double>>> ();
//		
//		List<Map<Integer, Double>> class1 = new LinkedList<Map<Integer, Double>>();
//		List<Map<Integer, Double>> class2 = new LinkedList<Map<Integer, Double>>();
//		for(String line : data) {
//			StringTokenizer st = new StringTokenizer(line, " ");
//			if(!st.hasMoreElements())
//				System.out.println("empty line");
//			//extract class (col1)
//			int classNo = new BigDecimal(st.nextElement().toString()).intValue();
//
//			//extract features
//			Map<Integer, Double> features = new HashMap<Integer, Double> ();
//			for(int i = 0; st.hasMoreElements(); i++){
//				Double val = new BigDecimal(st.nextElement().toString()).doubleValue();
//				features.put(i, val);
//			}
//			
//			//put in list
//			if(classNo == 1) 
//				class1.add(features);
//			else
//				class2.add(features);
//		}
//		
//		featureMap.put(1, class1);
//		featureMap.put(2, class2);
//		
//		return featureMap;
//	}
//	
//	public void printPairList(List<Pair> list) {
//		for(Pair p : list ) {
//			System.out.println("Class : " + p.getFirst() + "\tFeature : " + p.getSecond());
//		}
//	}
//	
//	public void printAllFeatureMap(Map<Integer, List<Pair>> sets){
//		for(Integer feature : sets.keySet()) {
//			for(Pair p : sets.get(feature)) {
//				System.out.println("Feature : " + feature + "\tClass : " + p.getFirst() + "\tVal : " + p.getSecond());
//			}
//		}
//	}
}
