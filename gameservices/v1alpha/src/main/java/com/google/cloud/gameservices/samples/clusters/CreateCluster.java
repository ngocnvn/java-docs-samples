/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.gameservices.samples.clusters;

// [START cloud_game_servers_cluster_create]

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.gaming.v1alpha.CreateGameServerClusterRequest;
import com.google.cloud.gaming.v1alpha.GameServerCluster;
import com.google.cloud.gaming.v1alpha.GameServerClusterConnectionInfo;
import com.google.cloud.gaming.v1alpha.GameServerClustersServiceClient;
import com.google.protobuf.Empty;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CreateCluster {

  public static void createGameServerCluster(
      String projectId, String regionId, String realmId, String clusterId, String gkeName) {
    // String projectId = "your-project-id";
    // String regionId = "us-central1-f";
    // String realmId = "your-realm-id";
    // String clusterId = "your-game-server-cluster-id";
    // String gkeName = "projects/your-project-id/locations/us-central1/clusters/test";
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests. After completing all of your requests, call
    // the "close" method on the client to safely clean up any remaining background resources.
    try (GameServerClustersServiceClient client = GameServerClustersServiceClient.create()) {
      String parent = String.format(
          "projects/%s/locations/%s/realms/%s", projectId, regionId, realmId);
      String clusterName = String.format("%s/gameServerClusters/%s", parent, clusterId);

      GameServerCluster gameServerCluster = GameServerCluster
          .newBuilder()
          .setName(clusterName)
          .setConnectionInfo(GameServerClusterConnectionInfo
              .newBuilder()
              .setGkeName(gkeName)
              .setNamespace("default"))
          .build();

      CreateGameServerClusterRequest request = CreateGameServerClusterRequest
          .newBuilder()
          .setParent(parent)
          .setGameServerClusterId(clusterId)
          .setGameServerCluster(gameServerCluster)
          .build();

      OperationFuture<GameServerCluster, Empty> call = client.createGameServerClusterAsync(request);

      GameServerCluster created = call.get(1, TimeUnit.MINUTES);
      System.out.println("Game Server Cluster created: " + created.getName());
    } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
      System.err.println("Game Server Cluster create request unsuccessful.");
      e.printStackTrace(System.err);
    }
  }
}
// [END cloud_game_servers_cluster_create]
