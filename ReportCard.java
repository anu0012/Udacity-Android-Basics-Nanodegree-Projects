public class ReportCard{

	// Grade variables of different subjects
	private String englishGrade;
	private String historyGrade;
	private String physicsGrade;
	private String chemistryGrade;
	private String mathematicsGrade;

	// Constructor
	public ReportCard(){
		englishGrade = "No Grade Yet";
		historyGrade = "No Grade Yet";
		physicsGrade = "No Grade Yet";
		chemistryGrade = "No Grade Yet";
		mathematicsGrade = "No Grade Yet";
	}

	//Setter Methods
	public void setGradeForEnglish(String s){
		englishGrade = s;
	}

	public void setGradeForHistory(String s){
		historyGrade = s;
	}

	public void setGradeForPhysics(String s){
		physicsGrade = s;
	}

	public void setGradeForChemistry(String s){
		chemistryGrade = s;
	}
	public void setGradeForMathematics(String s){
		mathematicsGrade = s;
	}

	//Getter Methods
	public String getGradeForEnglish(){
		return englishGrade;
	}

	public String getGradeForHistory(){
		return historyGrade;
	}

	public String getGradeForPhysics(){
		return physicsGrade;
	}

	public String getGradeForChemistry(){
		return chemistryGrade;
	}

	public String getGradeForMathematics(){
		return mathematicsGrade;
	}
	@Override
	public String toString(){
		return "Grades: \n"+"English Grades = "+englishGrade+"\nHistory Grades = "+historyGrade+"\nPhysics Grades = "+physicsGrade+
				"\nChemistry Grades = "+chemistryGrade+"\nMathematics Grades = "+mathematicsGrade;
	}
}