	.text
	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_bb:
	addi	sp, sp, -384
	mv	s1, s0
	addi	s0, sp, 384
	mv	t0, s1
	sw	t0, -36(s0)
	li	t0, 10000
	sw	t0, -40(s0)
	lw	t2, -40(s0)
	sw	t2, -8(s0)
	li	t0, 0
	sw	t0, -44(s0)
	lw	t2, -44(s0)
	sw	t2, -12(s0)
	li	t0, 2800
	sw	t0, -48(s0)
	lw	t2, -48(s0)
	sw	t2, -16(s0)
	li	t0, 0
	sw	t0, -52(s0)
	lw	t2, -52(s0)
	sw	t2, -20(s0)
	li	t0, 0
	sw	t0, -56(s0)
	lw	t2, -56(s0)
	sw	t2, -24(s0)
	li	t0, 2801
	sw	t0, -60(s0)
	li	t0, 8
	sw	t0, -64(s0)
	lw	t1, -60(s0)
	lw	t2, -64(s0)
	mul	t0, t1, t2
	sw	t0, -68(s0)
	lw	t1, -68(s0)
	addi	t0, t1, 4
	sw	t0, -72(s0)
	lw	t1, -72(s0)
	mv	a0, t1
	mv	t0, ra
	sw	t0, -76(s0)
	call	_f__malloc
	lw	t1, -76(s0)
	mv	ra, t1
	mv	t0, a0
	sw	t0, -80(s0)
	li	t0, 2801
	sw	t0, -84(s0)
	lw	t1, -80(s0)
	lw	t2, -84(s0)
	sw	t2, 0(t1)
	lw	t1, -80(s0)
	addi	t0, t1, 4
	sw	t0, -88(s0)
	lw	t2, -88(s0)
	sw	t2, -28(s0)
	li	t0, 0
	sw	t0, -92(s0)
	lw	t2, -92(s0)
	sw	t2, -32(s0)
	j	.for_condition_bb

.main_bb1:
	lw	t0, -4(s0)
	sw	t0, -96(s0)
	lw	t1, -96(s0)
	mv	a0, t1
	lw	t1, -36(s0)
	mv	s0, t1
	addi	sp, sp, 384
	ret	

.for_condition_bb:
	lw	t0, -16(s0)
	sw	t0, -100(s0)
	lw	t0, -12(s0)
	sw	t0, -104(s0)
	lw	t1, -104(s0)
	lw	t2, -100(s0)
	sub	t0, t1, t2
	sw	t0, -108(s0)
	li	t0, 0
	sw	t0, -112(s0)
	lw	t1, -108(s0)
	lw	t2, -112(s0)
	xor	t0, t1, t2
	sw	t0, -116(s0)
	lw	t1, -116(s0)
	snez	t0, t1
	sw	t0, -116(s0)
	lw	t1, -116(s0)
	bne	t1, zero, .for_body_bb
	j	.main_bb2

.for_iter_bb:
	lw	t0, -12(s0)
	sw	t0, -120(s0)
	lw	t1, -120(s0)
	addi	t0, t1, 1
	sw	t0, -124(s0)
	lw	t2, -124(s0)
	sw	t2, -12(s0)
	j	.for_condition_bb

.for_body_bb:
	lw	t0, -8(s0)
	sw	t0, -128(s0)
	li	t0, 5
	sw	t0, -132(s0)
	lw	t1, -128(s0)
	lw	t2, -132(s0)
	div	t0, t1, t2
	sw	t0, -136(s0)
	lw	t0, -28(s0)
	sw	t0, -140(s0)
	lw	t0, -12(s0)
	sw	t0, -144(s0)
	li	t0, 4
	sw	t0, -148(s0)
	lw	t1, -144(s0)
	lw	t2, -148(s0)
	mul	t0, t1, t2
	sw	t0, -152(s0)
	lw	t1, -140(s0)
	lw	t2, -152(s0)
	add	t0, t1, t2
	sw	t0, -156(s0)
	lw	t1, -156(s0)
	lw	t2, -136(s0)
	sw	t2, 0(t1)
	j	.for_iter_bb

.main_bb2:
	j	.for_condition_bb1

.for_condition_bb1:
	j	.for_body_bb1

.for_iter_bb1:
	lw	t0, -8(s0)
	sw	t0, -160(s0)
	lw	t0, -20(s0)
	sw	t0, -164(s0)
	lw	t1, -164(s0)
	lw	t2, -160(s0)
	rem	t0, t1, t2
	sw	t0, -168(s0)
	lw	t2, -168(s0)
	sw	t2, -24(s0)
	j	.for_condition_bb1

.for_body_bb1:
	li	t0, 0
	sw	t0, -172(s0)
	lw	t2, -172(s0)
	sw	t2, -20(s0)
	lw	t0, -16(s0)
	sw	t0, -176(s0)
	li	t0, 2
	sw	t0, -180(s0)
	lw	t1, -176(s0)
	lw	t2, -180(s0)
	mul	t0, t1, t2
	sw	t0, -184(s0)
	lw	t2, -184(s0)
	sw	t2, -32(s0)
	lw	t0, -32(s0)
	sw	t0, -188(s0)
	li	t0, 0
	sw	t0, -192(s0)
	lw	t1, -188(s0)
	lw	t2, -192(s0)
	xor	t0, t1, t2
	sw	t0, -196(s0)
	lw	t1, -196(s0)
	seqz	t0, t1
	sw	t0, -196(s0)
	lw	t1, -196(s0)
	bne	t1, zero, .if_then_bb
	j	.main_bb4

.main_bb3:
	la	t0, _str
	sw	t0, -200(s0)
	lw	t1, -200(s0)
	mv	a0, t1
	mv	t0, ra
	sw	t0, -204(s0)
	call	_f_print
	lw	t1, -204(s0)
	mv	ra, t1
	li	t0, 0
	sw	t0, -208(s0)
	lw	t2, -208(s0)
	sw	t2, -4(s0)
	j	.main_bb1

.if_then_bb:
	j	.main_bb3

.main_bb4:
	lw	t0, -16(s0)
	sw	t0, -212(s0)
	lw	t2, -212(s0)
	sw	t2, -12(s0)
	j	.for_condition_bb2

.for_condition_bb2:
	j	.for_body_bb2

.for_iter_bb2:
	lw	t0, -12(s0)
	sw	t0, -216(s0)
	lw	t0, -20(s0)
	sw	t0, -220(s0)
	lw	t1, -220(s0)
	lw	t2, -216(s0)
	mul	t0, t1, t2
	sw	t0, -224(s0)
	lw	t2, -224(s0)
	sw	t2, -20(s0)
	j	.for_condition_bb2

.for_body_bb2:
	lw	t0, -8(s0)
	sw	t0, -228(s0)
	lw	t0, -28(s0)
	sw	t0, -232(s0)
	lw	t0, -12(s0)
	sw	t0, -236(s0)
	li	t0, 4
	sw	t0, -240(s0)
	lw	t1, -236(s0)
	lw	t2, -240(s0)
	mul	t0, t1, t2
	sw	t0, -244(s0)
	lw	t1, -232(s0)
	lw	t2, -244(s0)
	add	t0, t1, t2
	sw	t0, -248(s0)
	lw	t1, -248(s0)
	lw	t0, 0(t1)
	sw	t0, -252(s0)
	lw	t1, -252(s0)
	lw	t2, -228(s0)
	mul	t0, t1, t2
	sw	t0, -256(s0)
	lw	t0, -20(s0)
	sw	t0, -260(s0)
	lw	t1, -260(s0)
	lw	t2, -256(s0)
	add	t0, t1, t2
	sw	t0, -264(s0)
	lw	t2, -264(s0)
	sw	t2, -20(s0)
	lw	t0, -32(s0)
	sw	t0, -268(s0)
	lw	t1, -268(s0)
	addi	t0, t1, -1
	sw	t0, -272(s0)
	lw	t2, -272(s0)
	sw	t2, -32(s0)
	lw	t0, -20(s0)
	sw	t0, -276(s0)
	lw	t1, -276(s0)
	lw	t2, -272(s0)
	rem	t0, t1, t2
	sw	t0, -280(s0)
	lw	t0, -28(s0)
	sw	t0, -284(s0)
	lw	t0, -12(s0)
	sw	t0, -288(s0)
	li	t0, 4
	sw	t0, -292(s0)
	lw	t1, -288(s0)
	lw	t2, -292(s0)
	mul	t0, t1, t2
	sw	t0, -296(s0)
	lw	t1, -284(s0)
	lw	t2, -296(s0)
	add	t0, t1, t2
	sw	t0, -300(s0)
	lw	t1, -300(s0)
	lw	t2, -280(s0)
	sw	t2, 0(t1)
	lw	t0, -32(s0)
	sw	t0, -304(s0)
	lw	t0, -20(s0)
	sw	t0, -308(s0)
	lw	t1, -308(s0)
	lw	t2, -304(s0)
	div	t0, t1, t2
	sw	t0, -312(s0)
	lw	t2, -312(s0)
	sw	t2, -20(s0)
	lw	t0, -32(s0)
	sw	t0, -316(s0)
	li	t0, 1
	sw	t0, -320(s0)
	lw	t1, -316(s0)
	lw	t2, -320(s0)
	sub	t0, t1, t2
	sw	t0, -324(s0)
	lw	t2, -324(s0)
	sw	t2, -32(s0)
	lw	t0, -12(s0)
	sw	t0, -328(s0)
	lw	t1, -328(s0)
	addi	t0, t1, -1
	sw	t0, -332(s0)
	lw	t2, -332(s0)
	sw	t2, -12(s0)
	li	t0, 0
	sw	t0, -336(s0)
	lw	t1, -332(s0)
	lw	t2, -336(s0)
	xor	t0, t1, t2
	sw	t0, -340(s0)
	lw	t1, -340(s0)
	seqz	t0, t1
	sw	t0, -340(s0)
	lw	t1, -340(s0)
	bne	t1, zero, .if_then_bb1
	j	.main_bb6

.main_bb5:
	lw	t0, -16(s0)
	sw	t0, -344(s0)
	li	t0, 14
	sw	t0, -348(s0)
	lw	t1, -344(s0)
	lw	t2, -348(s0)
	sub	t0, t1, t2
	sw	t0, -352(s0)
	lw	t2, -352(s0)
	sw	t2, -16(s0)
	lw	t0, -8(s0)
	sw	t0, -356(s0)
	lw	t0, -20(s0)
	sw	t0, -360(s0)
	lw	t1, -360(s0)
	lw	t2, -356(s0)
	div	t0, t1, t2
	sw	t0, -364(s0)
	lw	t0, -24(s0)
	sw	t0, -368(s0)
	lw	t1, -368(s0)
	lw	t2, -364(s0)
	add	t0, t1, t2
	sw	t0, -372(s0)
	lw	t1, -372(s0)
	mv	a0, t1
	mv	t0, ra
	sw	t0, -376(s0)
	call	_f_toString
	lw	t1, -376(s0)
	mv	ra, t1
	mv	t0, a0
	sw	t0, -380(s0)
	lw	t1, -380(s0)
	mv	a0, t1
	mv	t0, ra
	sw	t0, -384(s0)
	call	_f_print
	lw	t1, -384(s0)
	mv	ra, t1
	j	.for_iter_bb1

.if_then_bb1:
	j	.main_bb5

.main_bb6:
	j	.for_iter_bb2

	.size	main, .-main
			 # -- End function
	.type	_str,@object
	.section	.rodata
_str:
	.asciz	"\n"
	.size	_str, 2

