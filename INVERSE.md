# Operation

```Java
public abstract List<int[]> inverse(int min, int max, int size);

protected boolean exceedsMaxRepeats(int[] array) {
	Map<Integer, Integer> countMap = new HashMap<>();
	int maxRepeats = array.length / 2;

	for (int num : array) {
		countMap.put(num, countMap.getOrDefault(num, 0) + 1);
		if (countMap.get(num) > maxRepeats)
			return true;
	}

	return false;
}
```

## OperationAddition

### Implementation

```Java
@Override
public List<int[]> inverse(int min, int max, int size) {
}
```

### Results

```Java
```

## OperationMultiplication

### Implementation

```Java
@Override
public List<int[]> inverse(int min, int max, int size) {
}
```

### Results

```Java
```

## OperationGCD

### Implementation

```Java
@Override
public List<int[]> inverse(int min, int max, int size) {
	List<int[]> result = new ArrayList<>();

	generateNPlets(result, new int[size], max, 0, min);

	return result.isEmpty() ? null : result;
}

// TODO: can this be moved to Operation and change GCD() for calculate()?
// TODO: if so, can the inverse method be implemented there?
private void generateNPlets(List<int[]> result, int[] nPlet, int max, int index, int current) {
	if (index == nPlet.length) {
		if (GCD(nPlet) == target && !exceedsMaxRepeats(nPlet))
			result.add(nPlet.clone());
		return;
	}

	for (int i = current; i <= max; i++) {
		nPlet[index] = i;
		generateNPlets(result, nPlet, max, index + 1, i);
	}
}
```

### Results

```Java
OperationGCD op_2 = new OperationGCD(2);
OperationGCD op_3 = new OperationGCD(3);
op_2.inverse(1, 4, 2);	// <[2,4]>
op_2.inverse(1, 3, 2);	// null
op_3.inverse(1, 9, 2);	// <[3,6], [3,9], [6,9]>
op_3.inverse(1, 6, 2);	// <[3,6]>
op_3.inverse(1, 6, 3);	// null
op_3.inverse(1, 9, 3);	// <[3,3,6], [3,3,9], [3,6,6], [3,6,9], [3,9,9], [6,6,9], [6,9,9]>
op_3.inverse(1, 12, 4);	// <[3,3,6,6], [3,3,6,9], [3,3,6,12], [3,3,9,9], [3,3,9,12], [3,3,12,12], [3,3,12,12], [3,6,6,9], [3,6,6,12], [3,6,9,9], [3,6,9,12], [3,6,12,12], [3,9,9,12], [3,9,12,12], [6,6,9,9], [6,6,9,12], [6,9,9,12], [6,9,12,12], [9,9,12,12]>
```

## OperationLCM

### Implementation

```Java
@Override
public List<int[]> inverse(int min, int max, int size) {
}
```

### Results

```Java
```

# OperationLimitedOperands

```Java
@Override
public List<int[]> inverse(int min, int max, int size) {
	// TODO: check size == nOperands or throw error
	return inverse(min, max);
}

public abstract List<int[]> inverse(int min, int max);
```

## OperationSubtraction

### Implementation

```Java
@Override
public List<int[]> inverse(int min, int max) {
}
```

### Results

```Java
```

## OperationDivision

### Implementation

```Java
@Override
public List<int[]> inverse(int min, int max) {
}
```

### Results

```Java
```
