/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.monad;

import java.util.function.Function;

interface Monad<T> {
	<U> Monad<U> bind(Function<T, Monad<U>> f);
}

class Maybe<T> implements Monad<T> {

	private final T val;

	public Maybe(T val) {
		this.val = val;
	}

	public T getVal() {
		return val;
	}

	@Override
	public <U> Monad<U> bind(Function<T, Monad<U>> f) {
		if (val == null)
			return new Maybe<U>(null);
		return f.apply(val);
	}
}

public class MonadApp {
	public static void main(String[] args) {
		Maybe<Integer> x = new Maybe<>(5);
		Monad<Integer> y = x
				.bind(v -> new Maybe<>(v + 1))
				.bind(v -> new Maybe<>(v * 2));
		System.out.println( ((Maybe<Integer>)y).getVal() );
	}
}