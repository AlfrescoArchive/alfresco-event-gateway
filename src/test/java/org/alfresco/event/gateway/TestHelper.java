/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.event.gateway;

/**
 * @author Jamal Kaabi-Mofrad
 */
public class TestHelper
{
    public static final String RAW_NODE_ADDED_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.NodeAddedEvent\", \n" + "   \"id\": \"489ced52-9b4c-4d71-8c67-99b1d340c0e6\", \n"
                            + "   \"type\": \"NODEADDED\", \n" + "   \"username\": \"user1\", \n" + "   \"timestamp\": 1530107844393, \n"
                            + "   \"seqNumber\": 0, \n" + "   \"txnId\": \"c3a3705b-55a7-49ef-bc93-980f5d1250fb\", \n" + "   \"networkId\": \"\", \n"
                            + "   \"client\": null, \n" + "   \"nodeId\": \"44e0b8c4-8530-46d8-af0c-cca51eb81345\", \n"
                            + "   \"siteId\": \"testsite\", \n" + "   \"nodeType\": \"cm:content\", \n" + "   \"name\": \"testDoc.txt\", \n"
                            + "   \"nodeModificationTime\": 1530107844387, \n" + "   \"paths\": [\n" + "      \"java.util.ArrayList\", \n"
                            + "      [\n" + "         \"/Company Home/Sites/testsite/documentLibrary/Docs/testDoc.txt\"\n" + "      ]\n" + "   ], \n"
                            + "   \"parentNodeIds\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n" + "         [\n"
                            + "            \"java.util.ArrayList\", \n" + "            [\n"
                            + "               \"f743f8b7-677e-4613-84e9-cfc7e9399eca\", \n"
                            + "               \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "               \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n"
                            + "               \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "               \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n"
                            + "               \"b90e66df-347f-4480-81c9-554faa257483\"\n" + "            ]\n" + "         ]\n" + "      ]\n"
                            + "   ], \n" + "   \"aspects\": [\n" + "      \"java.util.HashSet\", \n" + "      [\n" + "         \"sys:localized\", \n"
                            + "         \"sys:referenceable\", \n" + "         \"cm:auditable\"\n" + "      ]\n" + "   ], \n"
                            + "   \"nodeProperties\": {\n" + "      \"@class\": \"java.util.HashMap\"\n" + "   }\n" + "}";

    public static final String PUBLIC_NODE_ADDED_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"NODEADDED\", \n"
                            + "   \"streamPosition\": \"1530653327117-mueyyf\", \n" + "   \"principal\": \"user1\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.NodeResourceV1\", \n"
                            + "      \"id\": \"489ced52-9b4c-4d71-8c67-99b1d340c0e6\", \n" + "      \"primaryHierarchy\": [\n" + "         {\n"
                            + "            \"id\": \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "            \"type\": \"Node\"\n" + "         }, \n" + "         {\n"
                            + "            \"id\": \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "            \"type\": \"Node\"\n" + "         }, \n" + "         {\n"
                            + "            \"id\": \"f743f8b7-677e-4613-84e9-cfc7e9399eca\", \n" + "            \"type\": \"Node\"\n" + "         }\n"
                            + "      ], \n" + "      \"nodeType\": \"cm:content\"\n" + "   }\n" + "}";

    public static final String RAW_PERMISSION_GRANTED_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.permission.LocalPermissionGrantedEvent\", \n"
                            + "   \"id\": \"df47afbf-534c-47e3-83c6-6b485fe94414\", \n" + "   \"type\": \"LOCALPERMISSIONGRANTED\", \n"
                            + "   \"username\": \"user1\", \n" + "   \"timestamp\": 1530135632551, \n" + "   \"seqNumber\": 2, \n"
                            + "   \"txnId\": \"fa18ca1c-8193-404c-bf27-6f51c2e86f99\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null, \n"
                            + "   \"nodeId\": \"f743f8b7-677e-4613-84e9-cfc7e9399eca\", \n" + "   \"siteId\": \"testsite\", \n"
                            + "   \"nodeType\": \"cm:folder\", \n" + "   \"name\": \"Docs\", \n" + "   \"nodeModificationTime\": 1530135566411, \n"
                            + "   \"paths\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n"
                            + "         \"/Company Home/Sites/testsite/documentLibrary/Docs\"\n" + "      ]\n" + "   ], \n"
                            + "   \"parentNodeIds\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n" + "         [\n"
                            + "            \"java.util.ArrayList\", \n" + "            [\n"
                            + "               \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "               \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n"
                            + "               \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "               \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n"
                            + "               \"b90e66df-347f-4480-81c9-554faa257483\"\n" + "            ]\n" + "         ]\n" + "      ]\n"
                            + "   ], \n" + "   \"aspects\": [\n" + "      \"java.util.HashSet\", \n" + "      [\n" + "         \"sys:localized\", \n"
                            + "         \"sys:referenceable\", \n" + "         \"cm:titled\", \n" + "         \"cm:auditable\"\n" + "      ]\n"
                            + "   ], \n" + "   \"nodeProperties\": {\n" + "      \"@class\": \"java.util.HashMap\"\n" + "   }, \n"
                            + "   \"authority\": \"GROUP_TestGroup\", \n" + "   \"permission\": \"SiteContributor\"\n" + "}";

    public static final String PUBLIC_PERMISSION_GRANTED_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"LOCALPERMISSIONGRANTED\", \n"
                            + "   \"streamPosition\": \"1530717626127-wmm7mw\", \n" + "   \"principal\": \"user1\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.PermissionResourceV1\", \n"
                            + "      \"id\": \"df47afbf-534c-47e3-83c6-6b485fe94414\", \n" + "      \"primaryHierarchy\": [\n" + "         {\n"
                            + "            \"id\": \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "            \"type\": \"Node\"\n" + "         }, \n" + "         {\n"
                            + "            \"id\": \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "            \"type\": \"Node\"\n" + "         }\n" + "      ], \n" + "      \"nodeType\": \"cm:folder\", \n"
                            + "      \"authority\": \"GROUP_TestGroup\", \n" + "      \"permission\": \"SiteContributor\"\n" + "   }\n" + "}";

    public static final String RAW_INHERIT_PERMISSION_DISABLED_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.permission.InheritPermissionsDisabledEvent\", \n"
                            + "   \"id\": \"0e53d41f-c6c5-4fea-b3e8-077224a499b1\", \n" + "   \"type\": \"INHERITPERMISSIONSDISABLED\", \n"
                            + "   \"username\": \"user1\", \n" + "   \"timestamp\": 1530135566424, \n" + "   \"seqNumber\": 1, \n"
                            + "   \"txnId\": \"676afae0-ca4b-4f68-b576-5888b51877ea\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null, \n"
                            + "   \"nodeId\": \"f743f8b7-677e-4613-84e9-cfc7e9399eca\", \n" + "   \"siteId\": \"testsite\", \n"
                            + "   \"nodeType\": \"cm:folder\", \n" + "   \"name\": \"Docs\", \n" + "   \"nodeModificationTime\": 1530135566411, \n"
                            + "   \"paths\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n"
                            + "         \"/Company Home/Sites/testsite/documentLibrary/Docs\"\n" + "      ]\n" + "   ], \n"
                            + "   \"parentNodeIds\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n" + "         [\n"
                            + "            \"java.util.ArrayList\", \n" + "            [\n"
                            + "               \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "               \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n"
                            + "               \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "               \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n"
                            + "               \"b90e66df-347f-4480-81c9-554faa257483\"\n" + "            ]\n" + "         ]\n" + "      ]\n"
                            + "   ], \n" + "   \"aspects\": [\n" + "      \"java.util.HashSet\", \n" + "      [\n" + "         \"sys:localized\", \n"
                            + "         \"sys:referenceable\", \n" + "         \"cm:titled\", \n" + "         \"cm:auditable\"\n" + "      ]\n"
                            + "   ], \n" + "   \"nodeProperties\": {\n" + "      \"@class\": \"java.util.HashMap\"\n" + "   }, \n"
                            + "   \"async\": false\n" + "}";

    public static final String Public_INHERIT_PERMISSION_DISABLED_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"INHERITPERMISSIONSDISABLED\", \n"
                            + "   \"streamPosition\": \"1530719120372-4dvaxq\", \n" + "   \"principal\": \"user1\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.PermissionResourceV1\", \n"
                            + "      \"id\": \"0e53d41f-c6c5-4fea-b3e8-077224a499b1\", \n" + "      \"primaryHierarchy\": [\n" + "         {\n"
                            + "            \"id\": \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "            \"type\": \"Node\"\n" + "         }, \n" + "         {\n"
                            + "            \"id\": \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "            \"type\": \"Node\"\n" + "         }\n" + "      ], \n" + "      \"nodeType\": \"cm:folder\", \n"
                            + "      \"async\": false\n" + "   }\n" + "}";

    public static final String RAW_INHERIT_PERMISSION_ENABLED_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.permission.InheritPermissionsEnabledEvent\", \n"
                            + "   \"id\": \"a1d3d41f-c6c5-4fea-b3e8-077224a4854g\", \n" + "   \"type\": \"INHERITPERMISSIONSENABLED\", \n"
                            + "   \"username\": \"user1\", \n" + "   \"timestamp\": 1530135786124, \n" + "   \"seqNumber\": 1, \n"
                            + "   \"txnId\": \"676afae0-ca4b-4f68-b576-5888b51877ea\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null, \n"
                            + "   \"nodeId\": \"f743f8b7-677e-4613-84e9-cfc7e9399eca\", \n" + "   \"siteId\": \"testsite\", \n"
                            + "   \"nodeType\": \"cm:folder\", \n" + "   \"name\": \"Docs\", \n" + "   \"nodeModificationTime\": 1530135566411, \n"
                            + "   \"paths\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n"
                            + "         \"/Company Home/Sites/testsite/documentLibrary/Docs\"\n" + "      ]\n" + "   ], \n"
                            + "   \"parentNodeIds\": [\n" + "      \"java.util.ArrayList\", \n" + "      [\n" + "         [\n"
                            + "            \"java.util.ArrayList\", \n" + "            [\n"
                            + "               \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "               \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n"
                            + "               \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "               \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n"
                            + "               \"b90e66df-347f-4480-81c9-554faa257483\"\n" + "            ]\n" + "         ]\n" + "      ]\n"
                            + "   ], \n" + "   \"aspects\": [\n" + "      \"java.util.HashSet\", \n" + "      [\n" + "         \"sys:localized\", \n"
                            + "         \"sys:referenceable\", \n" + "         \"cm:titled\", \n" + "         \"cm:auditable\"\n" + "      ]\n"
                            + "   ], \n" + "   \"nodeProperties\": {\n" + "      \"@class\": \"java.util.HashMap\"\n" + "   }\n" + "}";

    public static final String PUBLIC_INHERIT_PERMISSION_ENABLED_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"INHERITPERMISSIONSENABLED\", \n"
                            + "   \"streamPosition\": \"1530719120383-raguky\", \n" + "   \"principal\": \"user1\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.PermissionResourceV1\", \n"
                            + "      \"id\": \"a1d3d41f-c6c5-4fea-b3e8-077224a4854g\", \n" + "      \"primaryHierarchy\": [\n" + "         {\n"
                            + "            \"id\": \"ea74ec76-3b7d-4c0e-8d2c-2cd212e255a9\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"5ba21380-b302-4a86-984f-47eac9d5f058\", \n"
                            + "            \"type\": \"Node\"\n" + "         }, \n" + "         {\n"
                            + "            \"id\": \"c2346c1d-4859-490b-9cd2-49a8d54d11ed\", \n" + "            \"type\": \"Node\"\n"
                            + "         }, \n" + "         {\n" + "            \"id\": \"c23ed6ab-b8de-4daa-ad1d-b1c0aed3d651\", \n"
                            + "            \"type\": \"Node\"\n" + "         }\n" + "      ], \n" + "      \"nodeType\": \"cm:folder\"\n" + "   }\n"
                            + "}";

    public static final String RAW_AUTHORITY_ADDED_TO_GROUP_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.authority.AuthorityAddedToGroupEvent\", \n"
                            + "   \"id\": \"86525ef4-6c7c-4e82-94f4-4687a736f392\", \n" + "   \"type\": \"AUTHADDEDTOGROUP\", \n"
                            + "   \"username\": \"admin\", \n" + "   \"timestamp\": 1530135300066, \n" + "   \"seqNumber\": 1, \n"
                            + "   \"txnId\": \"911f78a1-11ff-457d-bb1d-c6ea94c49147\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null, \n"
                            + "   \"authorityName\": \"user2\", \n" + "   \"parentGroup\": \"GROUP_TestGroup\"\n" + "}";

    public static final String PUBLIC_AUTHORITY_ADDED_TO_GROUP_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"AUTHADDEDTOGROUP\", \n"
                            + "   \"streamPosition\": \"1530720801130-7qzaqm\", \n" + "   \"principal\": \"admin\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.AuthorityResourceV1\", \n"
                            + "      \"id\": \"86525ef4-6c7c-4e82-94f4-4687a736f392\", \n" + "      \"primaryHierarchy\": [], \n"
                            + "      \"authorityName\": \"user2\", \n" + "      \"parentGroup\": \"GROUP_TestGroup\"\n" + "   }\n" + "}";

    public static final String RAW_AUTHORITY_REMOVED_FROM_GROUP_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.authority.AuthorityRemovedFromGroupEvent\", \n"
                            + "   \"id\": \"65195ef4-6c7c-4e82-94f4-4687a736fa47\", \n" + "   \"type\": \"AUTHREMOVEDFROMGROUP\", \n"
                            + "   \"username\": \"admin\", \n" + "   \"timestamp\": 1530135412076, \n" + "   \"seqNumber\": 1, \n"
                            + "   \"txnId\": \"854f78a1-11ff-457d-bb1d-c6ea94c4932a\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null, \n"
                            + "   \"authorityName\": \"user2\", \n" + "   \"parentGroup\": \"GROUP_TestGroup\"\n" + "}";

    public static final String PUBLIC_AUTHORITY_REMOVED_FROM_GROUP_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"AUTHREMOVEDFROMGROUP\", \n"
                            + "   \"streamPosition\": \"1530720801159-sbczxa\", \n" + "   \"principal\": \"admin\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.AuthorityResourceV1\", \n"
                            + "      \"id\": \"65195ef4-6c7c-4e82-94f4-4687a736fa47\", \n" + "      \"primaryHierarchy\": [], \n"
                            + "      \"authorityName\": \"user2\", \n" + "      \"parentGroup\": \"GROUP_TestGroup\"\n" + "   }\n" + "}";

    public static final String RAW_GROUP_DELETED_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.authority.GroupDeletedEvent\", \n"
                            + "   \"id\": \"65195ef4-6c7c-4e82-94f4-4687a736fa47\", \n" + "   \"type\": \"GROUPDELETED\", \n"
                            + "   \"username\": \"admin\", \n" + "   \"timestamp\": 1530138957184, \n" + "   \"seqNumber\": 1, \n"
                            + "   \"txnId\": \"682f78a1-11ff-457d-bb1d-c6ea94c4b689\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null, \n"
                            + "   \"authorityName\": \"GROUP_TestGroup\", \n" + "   \"cascade\": true\n" + "}";

    public static final String PUBLIC_GROUP_DELETED_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"GROUPDELETED\", \n"
                            + "   \"streamPosition\": \"1530720801165-chodit\", \n" + "   \"principal\": \"admin\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.AuthorityResourceV1\", \n"
                            + "      \"id\": \"65195ef4-6c7c-4e82-94f4-4687a736fa47\", \n" + "      \"primaryHierarchy\": [], \n"
                            + "      \"authorityName\": \"GROUP_TestGroup\", \n" + "      \"cascade\": true\n" + "   }\n" + "}";

    public static final String RAW_TRANSACTION_COMMITTED_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.TransactionCommittedEvent\", \n"
                            + "   \"id\": \"7bb2aa23-fbf0-40cb-96cc-7f100e7dacb3\", \n" + "   \"type\": \"TRANSACTION_COMMITTED\", \n"
                            + "   \"username\": \"user1\", \n" + "   \"timestamp\": 1530107844744, \n" + "   \"seqNumber\": 2, \n"
                            + "   \"txnId\": \"c3a3705b-55a7-49ef-bc93-980f5d1250fb\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null\n"
                            + "}";

    public static final String PUBLIC_TRANSACTION_COMMITTED_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"TRANSACTION_COMMITTED\", \n"
                            + "   \"streamPosition\": \"1530723060695-hc88u2\", \n" + "   \"principal\": \"user1\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.ResourceV1\", \n"
                            + "      \"id\": \"7bb2aa23-fbf0-40cb-96cc-7f100e7dacb3\", \n" + "      \"primaryHierarchy\": []\n" + "   }\n" + "}";

    public static final String RAW_TRANSACTION_ROLLED_BACK_EVENT =
                "{\n" + "   \"@class\": \"org.alfresco.events.types.TransactionRolledBackEvent\", \n"
                            + "   \"id\": \"ba22f284-28ed-4b10-8ef6-17cc87298349\", \n" + "   \"type\": \"TRANSACTION_ROLLBACK\", \n"
                            + "   \"username\": \"user1\", \n" + "   \"timestamp\": 1530135370978, \n" + "   \"seqNumber\": 2, \n"
                            + "   \"txnId\": \"1cc37a40-948d-4d39-b8c9-e6649db7d9e7\", \n" + "   \"networkId\": \"\", \n" + "   \"client\": null\n"
                            + "}";

    public static final String PUBLIC_TRANSACTION_ROLLED_BACK_EVENT =
                "{\n" + "   \"schema\": \"org.alfresco.event.model.EventV1\", \n" + "   \"type\": \"TRANSACTION_ROLLBACK\", \n"
                            + "   \"streamPosition\": \"1530723060723-hfm47o\", \n" + "   \"principal\": \"user1\", \n" + "   \"resource\": {\n"
                            + "      \"schema\": \"org.alfresco.event.model.ResourceV1\", \n"
                            + "      \"id\": \"ba22f284-28ed-4b10-8ef6-17cc87298349\", \n" + "      \"primaryHierarchy\": []\n" + "   }\n" + "}";
}
