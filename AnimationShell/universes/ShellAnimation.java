
public class ShellAnimation implements Animation {

	private static int universeCount = 0;
	
	public static int getUniverseCount() {
		return universeCount;
	}

	public static void setUniverseCount(int count) {
		ShellAnimation.universeCount = count;
	}

	public Universe getNextUniverse() {

		universeCount++;
		
		if (universeCount == 1) {
			return new Level1Universe();
		} else if (universeCount == 2) {
			return new Level2Universe();
		} else if (universeCount == 3) {
			return new Level3Universe();
		} else if (universeCount == 4) {
			return new Level4Universe();
		} else {
			return null;
		}

	}
	
}
