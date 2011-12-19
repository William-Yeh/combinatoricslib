package org.paukov.combinatorics.combination.simple;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.combination.multi.MultiCombinationGenerator;

/**
 * Iterator of simple combinations
 * 
 * @author Dmytro Paukov
 * @see CombinatoricsVector
 * @see SimpleCombinationGenerator
 * @param <T>
 *            Type of elements in a combination
 */
public class SimpleCombinationIterator<T> extends
		Iterator<CombinatoricsVector<T>> {

	/**
	 * Generator
	 */
	protected final SimpleCombinationGenerator<T> _generator;

	/**
	 * Current simple combination
	 */
	protected CombinatoricsVector<T> _currentSimpleCombination = null;

	/**
	 * Current index of combination
	 */
	protected long _currentIndex = 0;

	/**
	 * Size of core set
	 */
	protected final int _lengthN;

	/**
	 * Size of combination (number of elements) to generate
	 */
	protected final int _lengthK;

	/**
	 * Helper array
	 */
	private int[] _bitVector = null;

	/**
	 * Criteria to stop iteration
	 */
	private int _endIndex = 0;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Generator of simple combinations
	 */
	public SimpleCombinationIterator(SimpleCombinationGenerator<T> generator) {
		_generator = generator;
		_lengthN = generator.getCoreObject().getSize();
		_lengthK = generator.getCombinationLength();
		_currentSimpleCombination = new CombinatoricsVector<T>();
		_bitVector = new int[_lengthK + 1];
		init();
	}

	/**
	 * Initial action for iteration
	 */
	private void init() {
		
		for (int i = 0; i <= _lengthK; i++) {
			_bitVector[i] = i;
		}
		if (_lengthN > 0) {
			_endIndex = 1;
		}
		_currentIndex = 0;
	}

	/**
	 * Returns current combination
	 */
	@Override
	public CombinatoricsVector<T> getCurrentItem() {
		return _currentSimpleCombination;
	}

	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean isDone() {
		return (_endIndex == 0) || (_lengthK > _lengthN);
	}

	/**
	 * Moves to the next combination
	 */
	@Override
	public CombinatoricsVector<T> next() {
		_currentIndex++;

		for (int i = 1; i <= _lengthK; i++) {
			int index = _bitVector[i] - 1;
			if (_generator.getCoreObject().getSize() > 0) {
				_currentSimpleCombination.setValue(i - 1, _generator
						.getCoreObject().getValue(index));
			}
		}

		_endIndex = _lengthK;

		while (_bitVector[_endIndex] == _lengthN - _lengthK + _endIndex) {
			_endIndex--;
			if (_endIndex == 0)
				break;
		}
		_bitVector[_endIndex]++;
		for (int i = _endIndex + 1; i <= _lengthK; i++) {
			_bitVector[i] = _bitVector[i - 1] + 1;
		}

		return getCurrentItem();
	}

	/**
	 * Returns current combination as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleCombinationIterator=[#" + _currentIndex + ", "
				+ _currentSimpleCombination + "]";
	}

}
