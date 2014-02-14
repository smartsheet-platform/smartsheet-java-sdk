package com.smartsheet.api.internal.util;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */

public class Util {

	public Util() {}
	
	/**
	 * Helper function that throws an IllegalArgumentException if one of the parameters is null.
	 * @param objects the paramters to 
	 */
	public static void throwIfNull(Object... objects) {
		for (Object obj : objects) {
			if(obj == null){
				throw new IllegalArgumentException();
			}
		}
	}
	
	public static void throwIfEmpty(String... strings) {
		for (String string : strings) {
			if(string != null && string.isEmpty()){
				throw new IllegalArgumentException();
			}
		}
	}

}
