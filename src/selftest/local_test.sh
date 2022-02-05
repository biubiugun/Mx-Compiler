clang -S -emit-llvm builtin.c -o builtin.ll -O0
clang builtin.ll self_llvm_test.ll -o code
./code
rm code
