package com.javahand.ep1sode.retrofit

data class KbroChannelProgramResult(
    val result: Boolean,
    val data:   List<KbroProgram>,
    val msg:    String
)
