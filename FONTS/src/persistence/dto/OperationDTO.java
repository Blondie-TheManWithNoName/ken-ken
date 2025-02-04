package persistence.dto;

import java.util.Map;

public class OperationDTO {
	private final Map<Integer, String> OPERATION_MAP = Map.of(
			1, "+",
			2, "-",
			3, "*",
			4, "/",
			5, "gcd",
			6, "lcm",
			7, "^",
			8, "="
	);

	private final int operationId;
	private final int target;

	public OperationDTO(int operationId, int target) {
		this.operationId = operationId;
		this.target = target;
	}

	public String getSymbol() {
		return OPERATION_MAP.get(operationId);
	}

	public int getTarget() {
		return target;
	}

	@Override
	public String toString() {
		return operationId + " " + target;
	}

	public static int getOperationId(String symbol) {
		for (Map.Entry<Integer, String> entry : new OperationDTO(0, 0).OPERATION_MAP.entrySet())
			if (entry.getValue().equals(symbol))
				return entry.getKey();
		return -1;
	}
}
