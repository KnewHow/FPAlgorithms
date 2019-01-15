#ifndef TREE
#define TREE
// define a element type
using ElementType = int;
// define a tree
struct Tree {
  ElementType value;
  struct Tree *left;
  struct Tree *right;
  struct Tree *parent;
};
using Te = struct Tree;
using PTree = struct Tree *;
#endif
