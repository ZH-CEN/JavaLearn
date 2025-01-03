import os

def merge_java_files(folder_path, output_file):
    with open(output_file, 'w', encoding='utf-8') as outfile:
        for filename in os.listdir(folder_path):
            if filename.endswith(".java"):
                with open(os.path.join(folder_path, filename), 'r', encoding='utf-8') as infile:
                    outfile.write(infile.read())
                    outfile.write('\n')

# 示例用法
if __name__ == "__main__":
    merge_java_files("/home/zchen/IdeaProjects/learn/src/Experiment/Exp5_6", "/home/zchen/IdeaProjects/learn/src/Experiment/Exp5_6/AllInOne.java")