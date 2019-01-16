#ifndef TREE
#define TREE
// define a element type
using ElementType = int;
// define a tree
struct Tree {
  ElementType value;
  int height;
  struct Tree *left;
  struct Tree *right;
};
using Te = struct Tree;
using PTree = struct Tree *;
#endif
