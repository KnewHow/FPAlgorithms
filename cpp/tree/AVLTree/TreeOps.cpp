#include<iostream>
#include<cstdlib>
#include<vector>
#include "Tree.h"

using std :: cout;
using std :: endl;
using std :: begin;
using std :: end;
using std :: vector;
using std :: cerr;

PTree RR(PTree root) {
  PTree parent = root->right;
  // update parent left
  root->right = parent->left;
  parent->left->parent = root;

  // update parent
  parent->left = root;
  root->parent = parent;
  return parent;
}

PTree allocTree(ElementType value) {
  PTree tmp = (PTree)malloc(sizeof(Te));
  tmp->parent = nullptr;
  tmp->left = nullptr;
  tmp->right =  nullptr;
  tmp->value = value;
  return tmp;
}

PTree insert(PTree root, ElementType value) {
  if(root == nullptr) {
    return allocTree(value);
  } else {
    PTree tmp = root;
    PTree parent = nullptr;
    while(tmp != nullptr) {
      parent = tmp;
      if(value < tmp->value) {
        tmp = tmp->left;
      } else {
        tmp = tmp->right;

      }
    }
    PTree leaf = allocTree(value);
    if(value < parent->value) {
      parent->left = leaf;
      leaf->parent = parent;
    } else {
      parent->right = leaf;
      leaf->parent =parent;
    }
    return root;
  }
}

PTree search(PTree root, ElementType value) {
  PTree tmp = root;
  while(tmp != nullptr) {
    if(value < tmp->value) {
      tmp = tmp->left;
    } else if(value > tmp->value) {
      tmp = tmp->right;
    } else {
      break;
    }
  }
  return tmp;
}

void middlePrint(PTree root) {
  if(root != nullptr) {
    middlePrint(root->left);
    cout << root->value << " " << endl;
    middlePrint(root->right);
  }
}

PTree buildTree(vector<int> v) {
  PTree root = nullptr;
  for(auto a : v) {
    root = insert(root, a);
  }
  return root;
}

int getHeight(PTree root) {
  if(root != nullptr) {
    int lh = getHeight(root->left);
    int rh = getHeight(root->right);
    return (lh > rh ? lh : rh) + 1;
  } else {
    return 0;
  }
}

ElementType getMax(PTree root) {
  PTree tmp = root;
  PTree parent = nullptr;
  while(tmp != nullptr) {
    parent = tmp;
    tmp = tmp->right;
  }
  if(parent != nullptr) {
    return parent->value;
  } else {
    cerr << "The tree is empty";
  }
}

ElementType getMin(PTree root) {
  PTree tmp = root;
  PTree parent = nullptr;
  while(tmp != nullptr) {
    parent = tmp;
    tmp = tmp->left;
  }
  if(parent != nullptr) {
    return parent->value;
  }else {
    cerr << "The tree is empty" << endl;
  }
}


int main () {
  // vector<int> v{1,6,5,2,4,3};
  vector<int> v{4,3,2,1,5,6};
  PTree root =  buildTree(v);
  middlePrint(root);
  PTree r = search(root, 5);
  cout << "search result is " << r->value << endl;
  int height = getHeight(root);
  cout << "tree height is " << height << endl;
  ElementType max = getMax(root);
  cout << "tree max node is " << max << endl;
  ElementType minV = getMin(root);
  cout << "tree min node is " << minV << endl;
  return 0;
}
