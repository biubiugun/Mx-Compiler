@n_glo = global i32 zeroinitializer, align 4
@r_glo = global i32 zeroinitializer, align 4
@c_glo = global i32 zeroinitializer, align 4
@i_glo = global i32 zeroinitializer, align 4
@j_glo = global i32 zeroinitializer, align 4
define i32 @main()	{
main_bb:
	%_return.alloc1 = alloca i32, align 4
	store i32 0, i32* @i_glo, align 4
	%z.alloc = alloca i32, align 4
	br label %for_condition_bb
main_bb1:					 ;preds = %main_bb2
	%_return.load1 = load i32, i32* %_return.alloc1, align 4
	ret i32 %_return.load1
for_condition_bb:					 ;preds = %main_bb, %for_iter_bb
	%i.load = load i32, i32* @i_glo, align 4
	%slt = icmp slt i32 %i.load, 5
	%zext_1 = zext i1 %slt to i8
	%trunc_1 = trunc i8 %zext_1 to i1
	br i1 %trunc_1, label %for_body_bb, label %main_bb2
for_iter_bb:					 ;preds = %main_bb3
	%i.load2 = load i32, i32* @i_glo, align 4
	%add1 = add i32 %i.load2, 1
	store i32 %add1, i32* @i_glo, align 4
	br label %for_condition_bb
for_body_bb:					 ;preds = %for_condition_bb
	store i32 0, i32* @j_glo, align 4
	br label %for_condition_bb1
main_bb2:					 ;preds = %for_condition_bb
	%c.load3 = load i32, i32* @c_glo, align 4
	%sub1 = sub i32 2, %c.load3
	store i32 %sub1, i32* %z.alloc, align 4
	%z.load = load i32, i32* %z.alloc, align 4
	call void @_f_printInt(i32 %z.load)
	store i32 0, i32* %_return.alloc1, align 4
	br label %main_bb1
for_condition_bb1:					 ;preds = %for_body_bb, %for_iter_bb1
	%j.load = load i32, i32* @j_glo, align 4
	%slt1 = icmp slt i32 %j.load, 5
	%zext_2 = zext i1 %slt1 to i8
	%trunc_2 = trunc i8 %zext_2 to i1
	br i1 %trunc_2, label %for_body_bb1, label %main_bb3
for_iter_bb1:					 ;preds = %main_bb4
	%j.load2 = load i32, i32* @j_glo, align 4
	%add = add i32 %j.load2, 1
	store i32 %add, i32* @j_glo, align 4
	br label %for_condition_bb1
for_body_bb1:					 ;preds = %for_condition_bb1
	%_call_f_getInt = call i32 @_f_getInt()
	store i32 %_call_f_getInt, i32* @n_glo, align 4
	%n.load = load i32, i32* @n_glo, align 4
	%eq = icmp eq i32 %n.load, 1
	%zext_3 = zext i1 %eq to i8
	%trunc_3 = trunc i8 %zext_3 to i1
	br i1 %trunc_3, label %if_then_bb1, label %main_bb4
main_bb3:					 ;preds = %for_condition_bb1
	br label %for_iter_bb
if_then_bb1:					 ;preds = %for_body_bb1
	%i.load1 = load i32, i32* @i_glo, align 4
	store i32 %i.load1, i32* @r_glo, align 4
	%j.load1 = load i32, i32* @j_glo, align 4
	store i32 %j.load1, i32* @c_glo, align 4
	br label %main_bb4
main_bb4:					 ;preds = %for_body_bb1, %if_then_bb1
	br label %for_iter_bb1
}
declare i32 @_f_getInt()
define i32 @_f_abs(i32 %_arg)	{
_f_abs_bb:
	%_return.alloc = alloca i32, align 4
	%c.alloc = alloca i32, align 4
	store i32 %_arg, i32* %c.alloc, align 4
	%c.load = load i32, i32* %c.alloc, align 4
	%sgt = icmp sgt i32 %c.load, 0
	%zext_ = zext i1 %sgt to i8
	%trunc_ = trunc i8 %zext_ to i1
	br i1 %trunc_, label %if_then_bb, label %_f_abs_bb2
_f_abs_bb1:					 ;preds = %if_then_bb, %_f_abs_bb2
	%_return.load = load i32, i32* %_return.alloc, align 4
	ret i32 %_return.load
if_then_bb:					 ;preds = %_f_abs_bb
	%c.load1 = load i32, i32* %c.alloc, align 4
	store i32 %c.load1, i32* %_return.alloc, align 4
	br label %_f_abs_bb1
_f_abs_bb2:					 ;preds = %_f_abs_bb, %if_then_bb
	%c.load2 = load i32, i32* %c.alloc, align 4
	%sub = sub i32 0, %c.load2
	store i32 %sub, i32* %_return.alloc, align 4
	br label %_f_abs_bb1
}
declare void @_f_printInt(i32)