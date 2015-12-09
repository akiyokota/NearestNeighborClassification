import java.util.List;
import java.util.Map;


public class NearestNeighborClassifier {
	
	private List< Map < Integer, Map< Integer, Double>>> data;
	private int numFeatures;
	private int numInstance;
	
	private int calcNumFeatures() {
		for( Map < Integer, Map< Integer, Double>> row : data) {
			for(Integer classNo : row.keySet()) {
				return row.get(classNo).keySet().size() ;
			}
		}
		return 0;
	}
	
	public double distanceCalc(double x, double y) {
		return (x-y) * (x-y);
	}
	
	
	public void travereExample () {
		for( Map < Integer, Map< Integer, Double>> row : data) {
			for(Integer classNo : row.keySet()) {	//class No
				for (Integer featureNo : row.get(classNo).keySet()) {	// feature no, value
					System.out.println("Class No : " + classNo + "\tFeature No : " + featureNo + "\tFeature Val: " + row.get(classNo).get(featureNo));
				}
			}
		}
	}
	
	public double getFeatureScore1D(int feature) {
		int correct = 0;
				
		for( Map < Integer, Map< Integer, Double>> row : data) {
			
			for(Integer classNO : row.keySet()) {	
				if(nearestNeighbor1D(row.get(classNO).get(feature), feature, classNO) == classNO) {
					correct = correct + 1;
				}
			}

		}

		return correct/new Double(numInstance);
	}
	
	//return the class of nearestNeighbor
	public int nearestNeighbor1D(double featureVal, int featureNo,  int classNo) {
		double nearestDistance = 5000;
		int nearestClass =0;
		for( Map < Integer, Map< Integer, Double>> row : data) {
			for(Integer classNO : row.keySet()) {	//class No
				double distance = distanceCalc(featureVal, row.get(classNO).get(featureNo));
				if(distance < nearestDistance && distance!=0) {
					nearestDistance = distance;
					nearestClass = classNO;
				}
			}
		}
		return nearestClass;
	}
	
	
	
	
	
	
	
	
	public List<Map<Integer, Map<Integer, Double>>> getData() {
		return data;
	}

	public void setData(List<Map<Integer, Map<Integer, Double>>> data) {
		this.data = data;
	}

	public int getNumFeatures() {
		return numFeatures;
	}

	public void setNumFeatures(int numFeatures) {
		this.numFeatures = numFeatures;
	}

	public NearestNeighborClassifier(List< Map < Integer, Map< Integer, Double>>> data) {
		this.data = data;
		this.numFeatures = calcNumFeatures();
		this.numInstance = data.size();
	}

	public int getNumInstance() {
		return numInstance;
	}

	public void setNumInstance(int numInstance) {
		this.numInstance = numInstance;
	}
	
	
//	private Map<Integer, List<Map<Integer, Double>>> data;
//	private int numFeatures;
//	
//	public NearestNeighborClassifier(Map<Integer, List<Map<Integer, Double>>> data) {
//		this.data = data;
//		this.numFeatures = extractAllFeatureSet().keySet().size();
//	}
//	
//	public Map<Integer, List<Map<Integer, Double>>> getData() {
//		return data;
//	}
//
//	public void setData(Map<Integer, List<Map<Integer, Double>>> data) {
//		this.data = data;
//	}
//
//	/*
//	 * K-fold cross validation
//	 * Accuracy = [# of correct] / [# of instance in DB]
//	 */
//	public double crossValidation(double correct) {
//		//needs to implement
//		return correct/getNumInstances();
//	}
//	
//	
//	public double distanceCalc(double x, double y) {
//		return (x-y) * (x-y);
//	}
//	
//	public Pair getNearestNeighbor(double x, List<Pair> featureSet) {
//		Pair nearest = null;
//		double nearestDistance = 5000;
//		for (Pair p : featureSet){
//			if(nearest==null)
//				nearest = p;
//			double distance = distanceCalc(x, p.getSecond());
//			if(distance < nearestDistance && distance!=0) {
//				nearest = p;
//				nearestDistance =distance;
//			}
//		}		
//		return nearest;
//	}
//	
//	public int getNumInstances() {
//		int totalCount = 0;
//		for(int classNo : data.keySet()) { //for every class
//			for(Map<Integer, Double> features : data.get(classNo)){ //for each row of features
//				totalCount = totalCount + 1;
//			}
//		}
//		return totalCount;
//	}
//	
//	public List<Pair> extractFeatureSet(int featureNo) {
//		List<Pair> featureSet = new LinkedList<Pair> ();
//		for(int classNo: data.keySet()) {
//			for(Map<Integer, Double> features: data.get(classNo)){
//				featureSet.add(new Pair(classNo, features.get(featureNo)));
//			}
//		}
//		return featureSet;
//	}
//	
//	
//
//	public int oneDimentionEvaluation(List<Pair> featureSet) {
//		int numCorrect = 0;
//		for (Pair p : featureSet){
//			Pair nearestNeighbor = getNearestNeighbor(p.getSecond(), featureSet);
//			if(nearestNeighbor.getFirst().equals(p.getFirst())) {
//				numCorrect = numCorrect + 1;
//			}
//		}
//		return numCorrect;
//	}
//
//	public void f2(List<Pair> featureSet) {
//		
//	}
//	
//	
//	public Map<Integer, List<Pair>> extractAllFeatureSet() {
//		Map<Integer, List<Pair>> allFeatureSet = new HashMap<Integer, List<Pair>>();
//		
//		for(int classNo : data.keySet()) { //for every class
//			for(Map<Integer, Double> features : data.get(classNo)){ //for each row of features
//				for(int i = 0; features.containsKey(i); i++) {	//features of each column
//					//System.out.println("Class NO" + classNo + "\t" + "Feature" + i + " : " + features.get(i));
//					Pair set = new Pair(classNo, features.get(i));
//					if(allFeatureSet.containsKey(i)) {
//						allFeatureSet.get(i).add(set);
//					} else {
//						List<Pair> list= new LinkedList<Pair> ();
//						list.add(set);
//						allFeatureSet.put(i, list);
//					}
//				}
//			}
//		}
//		
//		return allFeatureSet;
//	}
//	
//	public Pair findBestFeature(Map<Integer, List<Pair>> allFeatureSet) {
//		double bestScore = 0.0;
//		int bestFeature = -1;
//		for(Integer feature: allFeatureSet.keySet()) {
//			double score = crossValidation(oneDimentionEvaluation(allFeatureSet.get(feature)));
//			if(score>bestScore) {
//				bestFeature = feature;
//				bestScore = score;
//			}
//			//System.out.println("Feature " + feature + "\tScore : " + score);
//			
//		}
//		return new Pair(bestFeature, bestScore);
//	}
//	
//	public Map<Integer, Boolean> bestComboInit() {
//		Map<Integer, Boolean> init = new HashMap<Integer, Boolean> ();
//		for(Integer feature: extractAllFeatureSet().keySet() ) {
//			init.put(feature, false);
//		}
//		return init;
//	}
//	
//	public boolean isFeatureTrue(int feature, Map<Integer, Boolean> bestCombo) {
//		return bestCombo.get(feature);
//	}
//	
//	public List<Integer> getCurrentFeatures(Map<Integer, Boolean> bestCombo) {
//		List<Integer> currentFeatures = new LinkedList<Integer> ();
//		for(Integer feature: bestCombo.keySet()) {
//			if(bestCombo.get(feature) == true){
//				currentFeatures.add(feature);
//			}
//		}
//		return currentFeatures;
//	}
//	
//	public void forwardSelection() {
//		Map<Integer, Boolean> bestCombo = bestComboInit();
//		
//		//find first feature and set it to true;
//		Map<Integer, List<Pair>> allFeatureSet = extractAllFeatureSet();
//		Pair bestFeature = findBestFeature(allFeatureSet);
//		bestCombo.put(bestFeature.getFirst(), true);
//
//		double currentScore = bestFeature.getSecond();
//		
//		for(int i = 0; i< numFeatures; i++){
//			if(bestCombo.get(i)==false){
//				
//			}
//		}
//		
//	}
//	
//	
//	//get number of corrects
//	public int f1(Map<Integer,List<Pair>> allFeatureSet, List<Integer> currentFeatures, Integer considerFeature ) {
//		int numCorrect = 0;
//		
//		//build a new list with new condiered feature into it
//		List<Integer> newCurrentFeatures = new LinkedList<Integer> ();
//		for(Integer i : currentFeatures) 
//			newCurrentFeatures.add(i);
//		newCurrentFeatures.add(considerFeature) ;
//		
////		for(Integer feature: allFeatureSet.keySet()) {
////			double totalDist = 0;
////			if(newCurrentFeatures.contains(feature)) {
////				double dist = 
////				
////				
////				
////			}
////		}
////		
////		
//		
//		return 0;
//	}
//	
//	public void nearestNeighborND(){
//		
//	}
	
}
