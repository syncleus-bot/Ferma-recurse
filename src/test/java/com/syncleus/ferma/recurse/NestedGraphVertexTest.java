/******************************************************************************
 *                                                                            *
 * Copyright: (c) Syncleus, Inc.                                              *
 *                                                                            *
 * You may redistribute and modify this source code under the terms and       *
 * conditions of the Open Source Community License - Type C version 1.0       *
 * or any later version as published by Syncleus, Inc. at www.syncleus.com.   *
 * There should be a copy of the license included with this file. If a copy   *
 * of the license is not included you are granted no right to distribute or   *
 * otherwise use this file except through a legal and valid license. You      *
 * should also contact Syncleus, Inc. at the information below if you cannot  *
 * find a license:                                                            *
 *                                                                            *
 * Syncleus, Inc.                                                             *
 * 2604 South 12th Street                                                     *
 * Philadelphia, PA 19148                                                     *
 *                                                                            *
 ******************************************************************************/
package com.syncleus.ferma.recurse;

import junit.framework.Assert;
import org.junit.Test;

public class NestedGraphVertexTest {
  @Test
  public void testCreateSubnodes() {
    final RecursiveGraph graph = new TinkerRecursiveGraphFactory(true, true).subgraph("0");

    //make sure we started with an empty graph
    Assert.assertEquals(0, graph.v().aggregate().count());

    // construct graph
    NestedGod nestedGod = graph.addFramedVertex(NestedGod.class);
    Assert.assertEquals(1, graph.v().aggregate().count());

    Assert.assertEquals(0, nestedGod.countSubnodes());
    nestedGod.createSubgraph();
    Assert.assertEquals(3, nestedGod.countSubnodes());
    Assert.assertEquals(1, graph.v().aggregate().count());

    //reaquire the nested object and make sure it still has 3 subnodes
    nestedGod = graph.v().next(NestedGod.class);
    Assert.assertEquals(3, nestedGod.countSubnodes());
  }
}
