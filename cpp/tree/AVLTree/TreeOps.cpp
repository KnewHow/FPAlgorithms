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

int getMax(int a, int b) {
  return a > b ? a : b;
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

PTree RR(PTree root) {
  PTree parent = root->right;
  // update parent left
  root->right = parent->left;

  // update parent
  parent->left = root;

  root->height = getMax(getHeight(root->left), getHeight(root->right)) + 1;
  parent->height = getMax(getHeight(parent->left), getHeight(parent->right)) + 1;
  return parent;
}

PTree LL(PTree root) {
  PTree parent = root->left;

  root->left = parent->right;

  parent->right = root;
  root->height = getMax(getHeight(root->left), getHeight(root->right)) + 1;
  parent->height = getMax(getHeight(parent->left), getHeight(parent->right)) + 1;
  return parent;
}

PTree LR(PTree root) {
  PTree r = RR(root);
  root->left = r;
  return LL(root);
}

PTree RL(PTree root) {
  PTree r = LL(root->right);
  root -> right = r;
  return RR(root);
}

PTree allocTree(ElementType value) {
  PTree tmp = (PTree)malloc(sizeof(Te));
  tmp->left = nullptr;
  tmp->right =  nullptr;
  tmp->value = value;
  tmp->height = 0;
  return tmp;
}

PTree insertALV(PTree root, ElementType value) {
  if(root == nullptr) {
    root = allocTree(value);
  } else {
    if(value < root->value) {
      root->left = insertALV(root->left, value);
      // insert node in left's left, LL
      if(getHeight(root->left) -  getHeight(root->right) > 1) {
        if(value < root->left->value) {
          root = LL(root);
        } else {
          root = LR(root);
        }
      }
    } else if(value > root->value) {
      root->right = insertALV(root->right,value);
      if(getHeight(root->right) -  getHeight(root->left) > 1) {
        if(value > root->right->value) {
          root = RR(root);
        } else {
          root = RL(root);
        }
      }
    }
  }

  root->height = getMax(getHeight(root->right), getHeight(root->left)) + 1;
  return root;
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
    root = insertALV(root, a);
  }
  return root;
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

PTree getMinNode(PTree root) {
  PTree tmp = root;
  PTree parent = nullptr;
  while(tmp != nullptr) {
    parent = tmp;
    tmp = tmp->left;
  }
  if(parent != nullptr) {
    return parent;
  }else {
    cerr << "The tree is empty" << endl;
  }
}

PTree deleteElement(PTree root, ElementType value) {
  PTree tmp = nullptr;
  if(root == nullptr) {
    return root;
  } else {
    if(value < root->value) {
      root->left =  deleteElement(root->left,value);
    } else if (value > root->value){
      root->right = deleteElement(root->right,value);
    } else {
      tmp = root;
      if(root->left != nullptr && root->right != nullptr) {
        tmp = getMinNode(root->right);
        root->value = tmp->value;
        root->right = deleteElement(root->right, tmp->value);
      } else if(root->left == nullptr) {
        root = root->right;
      } else if(root -> right == nullptr) {
        root = root->left;
      }
      free(tmp);
      return root;
    }
  }
}


int main () {
  vector<int> v{1,2,3,4,5,6};
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
  PTree r2 =  deleteElement(root, 5);
  middlePrint(r2);
  return 0;
}
