@_str = private unnamed_addr constant [2xi8] c"\0A\00", align 1
define i32 @main()	{
main_bb:
	%_return_alloc = alloca i32, align 4
	%a_alloc = alloca i32, align 4
	store i32 10000, i32* %a_alloc, align 4
	%b_alloc = alloca i32, align 4
	store i32 0, i32* %b_alloc, align 4
	%c_alloc = alloca i32, align 4
	store i32 2800, i32* %c_alloc, align 4
	%d_alloc = alloca i32, align 4
	store i32 0, i32* %d_alloc, align 4
	%e_alloc = alloca i32, align 4
	store i32 0, i32* %e_alloc, align 4
	%f_alloc = alloca i32*, align 8
	%mul = mul i32 2801, 8
	%add = add i32 %mul, 4
	%_call_f__malloc = call i8* @_f__malloc(i32 %add)
	%_call_f__malloc_bitcast = bitcast i8* %_call_f__malloc to i32*
	store i32 2801, i32* %_call_f__malloc_bitcast, align 4
	%_getelementptr = getelementptr inbounds i32, i32* %_call_f__malloc_bitcast, i32 1
	%_getelementptr_bitcast = bitcast i32* %_getelementptr to i32**
	store i32** %_getelementptr_bitcast, i32** %f_alloc, align 8
	%g_alloc = alloca i32, align 4
	store i32 0, i32* %g_alloc, align 4
	br label %for_condition_bb
main_bb1:					 ;preds = %main_bb3
	%_return_load = load i32, i32* %_return_alloc, align 4
	ret i32 %_return_load
for_condition_bb:					 ;preds = %main_bb, %for_iter_bb
	%c_load = load i32, i32* %c_alloc, align 4
	%b_load = load i32, i32* %b_alloc, align 4
	%sub = sub i32 %b_load, %c_load
	%ne = icmp ne i32 %sub, 0
	%zext_ = zext i1 %ne to i8
	%_trunc = trunc i8 %zext_ to i1
	br i1 %_trunc, label %for_body_bb, label %main_bb2
for_iter_bb:					 ;preds = %for_body_bb
	%b_load2 = load i32, i32* %b_alloc, align 4
	%add1 = add i32 %b_load2, 1
	store i32 %add1, i32* %b_alloc, align 4
	br label %for_condition_bb
for_body_bb:					 ;preds = %for_condition_bb
	%a_load = load i32, i32* %a_alloc, align 4
	%sdiv = sdiv i32 %a_load, 5
	%_array_load = load i32*, i32** %f_alloc, align 8
	%b_load1 = load i32, i32* %b_alloc, align 4
	%_getelementptr1 = getelementptr inbounds i32, i32* %_array_load, i32 %b_load1
	store i32 %sdiv, i32* %_getelementptr1, align 4
	br label %for_iter_bb
main_bb2:					 ;preds = %for_condition_bb
	br label %for_condition_bb1
for_condition_bb1:					 ;preds = %main_bb2, %for_iter_bb1
	br label %for_body_bb1
for_iter_bb1:					 ;preds = %main_bb5
	%a_load3 = load i32, i32* %a_alloc, align 4
	%d_load5 = load i32, i32* %d_alloc, align 4
	%srem1 = srem i32 %d_load5, %a_load3
	store i32 %srem1, i32* %e_alloc, align 4
	br label %for_condition_bb1
for_body_bb1:					 ;preds = %for_condition_bb1
	store i32 0, i32* %d_alloc, align 4
	%c_load1 = load i32, i32* %c_alloc, align 4
	%mul1 = mul i32 %c_load1, 2
	store i32 %mul1, i32* %g_alloc, align 4
	%g_load = load i32, i32* %g_alloc, align 4
	%eq = icmp eq i32 %g_load, 0
	%zext_1 = zext i1 %eq to i8
	%_trunc1 = trunc i8 %zext_1 to i1
	br i1 %_trunc1, label %if_then_bb, label %main_bb4
main_bb3:					 ;preds = %if_then_bb
	%_getelementptr4 = getelementptr inbounds [2xi8], [2xi8]* @_str, i32 0, i32 0
	call void @_f_print(i8* %_getelementptr4)
	store i32 0, i32* %_return_alloc, align 4
	br label %main_bb1
if_then_bb:					 ;preds = %for_body_bb1
	br label %main_bb3
main_bb4:					 ;preds = %for_body_bb1, %if_then_bb
	%c_load2 = load i32, i32* %c_alloc, align 4
	store i32 %c_load2, i32* %b_alloc, align 4
	br label %for_condition_bb2
for_condition_bb2:					 ;preds = %main_bb4, %for_iter_bb2
	br label %for_body_bb2
for_iter_bb2:					 ;preds = %main_bb6
	%b_load6 = load i32, i32* %b_alloc, align 4
	%d_load3 = load i32, i32* %d_alloc, align 4
	%mul3 = mul i32 %d_load3, %b_load6
	store i32 %mul3, i32* %d_alloc, align 4
	br label %for_condition_bb2
for_body_bb2:					 ;preds = %for_condition_bb2
	%a_load1 = load i32, i32* %a_alloc, align 4
	%_array_load1 = load i32*, i32** %f_alloc, align 8
	%b_load3 = load i32, i32* %b_alloc, align 4
	%_getelementptr2 = getelementptr inbounds i32, i32* %_array_load1, i32 %b_load3
	%_array_load2 = load i32, i32* %_getelementptr2, align 4
	%mul2 = mul i32 %_array_load2, %a_load1
	%d_load = load i32, i32* %d_alloc, align 4
	%add2 = add i32 %d_load, %mul2
	store i32 %add2, i32* %d_alloc, align 4
	%g_load1 = load i32, i32* %g_alloc, align 4
	%add3 = add i32 %g_load1, -1
	store i32 %add3, i32* %g_alloc, align 4
	%d_load1 = load i32, i32* %d_alloc, align 4
	%srem = srem i32 %d_load1, %add3
	%_array_load3 = load i32*, i32** %f_alloc, align 8
	%b_load4 = load i32, i32* %b_alloc, align 4
	%_getelementptr3 = getelementptr inbounds i32, i32* %_array_load3, i32 %b_load4
	store i32 %srem, i32* %_getelementptr3, align 4
	%g_load2 = load i32, i32* %g_alloc, align 4
	%d_load2 = load i32, i32* %d_alloc, align 4
	%sdiv1 = sdiv i32 %d_load2, %g_load2
	store i32 %sdiv1, i32* %d_alloc, align 4
	%g_load3 = load i32, i32* %g_alloc, align 4
	%sub1 = sub i32 %g_load3, 1
	store i32 %sub1, i32* %g_alloc, align 4
	%b_load5 = load i32, i32* %b_alloc, align 4
	%add4 = add i32 %b_load5, -1
	store i32 %add4, i32* %b_alloc, align 4
	%eq1 = icmp eq i32 %add4, 0
	%zext_2 = zext i1 %eq1 to i8
	%_trunc2 = trunc i8 %zext_2 to i1
	br i1 %_trunc2, label %if_then_bb1, label %main_bb6
main_bb5:					 ;preds = %if_then_bb1
	%c_load3 = load i32, i32* %c_alloc, align 4
	%sub2 = sub i32 %c_load3, 14
	store i32 %sub2, i32* %c_alloc, align 4
	%a_load2 = load i32, i32* %a_alloc, align 4
	%d_load4 = load i32, i32* %d_alloc, align 4
	%sdiv2 = sdiv i32 %d_load4, %a_load2
	%e_load = load i32, i32* %e_alloc, align 4
	%add5 = add i32 %e_load, %sdiv2
	%_call_f_toString = call i8* @_f_toString(i32 %add5)
	call void @_f_print(i8* %_call_f_toString)
	br label %for_iter_bb1
if_then_bb1:					 ;preds = %for_body_bb2
	br label %main_bb5
main_bb6:					 ;preds = %for_body_bb2, %if_then_bb1
	br label %for_iter_bb2
}
declare void @_f_print(i8*)
declare i8* @_f_toString(i32)
declare i8* @_f__malloc(i32)

