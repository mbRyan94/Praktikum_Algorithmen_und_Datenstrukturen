package edu.hm.cs.algdat.sequence;

import org.apache.commons.lang.StringUtils;

/**
 * Implementierung einer Sequenz, bei der die Werte in einem Array abgelegt
 * werden, das bei Bedarf dynamisch vergrößert wird.
 *
 */
public class ArraySequence implements Sequence {

	/** Das unterliegende Array zum Abspeichern der Werte */
	private Object[] array = new Object[2];

	/**
	 * Anzahl der enthaltenen Werte. Nur für die Indizes {@code i<size} enthält
	 * das zugrundliegende Array relevante Werte. Die Felder des Arrays mit
	 * größeren Indizes sind "frei".
	 */
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Sequenz hat kein Element " + index + ".");
		}
		return array[index];
	}

	/**
	 * Stellt sicher, dass das Array groß genug ist, um {@code needeSize}
	 * Elemente zu fassen. Wenn das bestehende Array dafür zu klein ist, wird es
	 * durch ein neues Array der Größe {@code size*2} ersetzt und die
	 * bestehenden Werte umkopiert.
	 * 
	 * @param capacity
	 *            Mindestgröße, die das Array für eine bevorstehende
	 *            (Einfüge-)Operation haben muss
	 */
	private void assertCapacity(int capacity) {

		// Wenn das Array groß genug ist, ist nichts zu tun.
		if (array.length >= capacity) {
			return;
		} else {

			// Erstelle neues Array doppelter Größe
			Object[] newArray = new Object[capacity * 2];

			// Kopiere Werte aus dem alten Array in das neue
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}

			// Ersetze das zugrundeliegende Array durch das neue
			array = newArray;
		}
	}

	@Override
	public String toString() {
		return "ArraySequence[" + StringUtils.join(array, ", ", 0, size) + "]";
	}

	/* Hier beginnen die Operationen aus dem Praktikum */

	@Override
	public void addLast(Object element) {
		// Implementierung in Aufgabe 1
		assertCapacity(size + 1);
		array[size] = element;
		size++;
	}

	@Override
	public void insert(int index, Object element) throws IndexOutOfBoundsException {
		// Implementierung in Aufgabe 2
		if ((index <= size) && (index >= 0)) {
			assertCapacity(size + 1);
			for (int a = size; a >= index; a--) {
				array[a + 1] = array[a];
			}
			array[index] = element;
			size++;
		} else {
			throw new IndexOutOfBoundsException("Sequenz hat kein Element " + index + ".");
		}
	}

	@Override
	public void delete(int index) throws IndexOutOfBoundsException {
		// Implementierung in Aufgabe 3
		if ((index <= size) && (index >= 0)) {
			for (int a = index; a < size; a++) {
				array[a] = array[a + 1];
			}
			size--;
		} else {
			throw new IndexOutOfBoundsException("Sequenz hat kein Element " + index + ".");
		}
	}
}
