package com.minerarcana.occult.multiblocksystem.validation;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ValidationError {

	public static final ValidationError VALIDATION_ERROR_TOO_FEW_PARTS = new ValidationError(
			"base.api.multiblock.validation.too_few_parts");

	public ValidationError(String messageFormatStringResourceKey, Object... messageParameters) {
		_resourceKey = messageFormatStringResourceKey;
		_parameters = messageParameters;
	}

	public ITextComponent getChatMessage() {
		return new TranslationTextComponent(_resourceKey, _parameters);
	}

	protected final String _resourceKey;
	protected final Object[] _parameters;
}
