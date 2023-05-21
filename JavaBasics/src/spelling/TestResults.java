package spelling;

/**
 * @author Tony
 * This stores the results of a graded test
 * ----You do not need to edit or change this class at all----
 */
public class TestResults 
{
	public int totalWords;
	public int correct;
	public int incorrectAttempts; // Even for words eventually correct
	public long totalDuration;
	
	@Override
	public String toString() 
	{
		return "TestResults [totalWords=" + totalWords + ", correct=" + correct + ", incorrectAttempts="
				+ incorrectAttempts + ", totalDuration=" + totalDuration + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestResults other = (TestResults) obj;
		if (correct != other.correct)
			return false;
		if (incorrectAttempts != other.incorrectAttempts)
			return false;
		if (totalDuration != other.totalDuration)
			return false;
		if (totalWords != other.totalWords)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + correct;
		result = prime * result + incorrectAttempts;
		result = prime * result + (int) (totalDuration ^ (totalDuration >>> 32));
		result = prime * result + totalWords;
		return result;
	}	
}