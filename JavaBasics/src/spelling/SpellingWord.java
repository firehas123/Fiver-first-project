package spelling;

/**
 * @author Tony
 * This stores the contents of the spelling word file
 * ----You do not need to edit or change this class at all----
 */
public class SpellingWord
{
	private String word;
	private String audioFile;
	
	public SpellingWord(String word, String audioFile)
	{
		this.word = word;
		this.audioFile = audioFile;
	}

	public String getWord()
	{
		return word;
	}

	public String getAudioFile()
	{
		return audioFile;
	}

	@Override
	public String toString() {
		return "SpellingWord [word=" + word + ", audioFile=" + audioFile + "]";
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpellingWord other = (SpellingWord) obj;
		if (audioFile == null) {
			if (other.audioFile != null)
				return false;
		} else if (!audioFile.equals(other.audioFile))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audioFile == null) ? 0 : audioFile.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}
}